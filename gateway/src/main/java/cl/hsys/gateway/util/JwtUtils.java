package cl.hsys.gateway.util;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

//Este componente se encarga de leer el token
@Component
public class JwtUtils {

    /* Inyección del secreto
    Busca en los archivos de configuración (application.yml o
    variables de entorno .env) el valor de app.jwt.secret*/
    @Value("${app.jwt.secret}")
    private String jwtSecret;

    /* Generación de la llave de firma (getSigningKey)
    transforma el String de texto plano en un objeto SecretKey
    compatible con algoritmo HMAC-SHA
    */
    private SecretKey getSigningKey() {
        //toma el string base64 y lo convierte en bytes originales
        byte[] keyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Validación del token
    public boolean isInvalid(String token){
        try{
            Jwts.parserBuilder()
                .setSigningKey(getSigningKey()) // Configura la llave para comparar.
                .build()
                .parseClaimsJws(token); // Intenta abrir el token
            return false; // Token valido
        } catch (Exception e) {
            return true; // Token invalido
        }
    }

    /* Extracción de datos (getClaims) 
    una vez que se ha validado que es legal,
    queremos leer que hay dentro, el payload. 
    */
    public Claims getClaims(String token) {
        return Jwts.parserBuilder() //quiero un lector de tokens
                    .setSigningKey(getSigningKey()) //utilizar esta llavepara verificar la firma
                    .build() //ensambla el lector de configuración
                    .parseClaimsJws(token) //
                    .getBody(); // Devuelve el mapa de datos (Claims)
    }
    
}
