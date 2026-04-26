package cl.hsys.gateway.filter;

import java.util.List;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import cl.hsys.gateway.util.JwtUtils;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config>{

    private final JwtUtils jwtUtils;

    public static final List<String> EXCLUDED_PATHS = List.of(
        "/api/auth/register",
        "/api/auth/login",
        "/actuator"
    );

    public AuthenticationFilter(JwtUtils jwtUtils) {
        super(Config.class);
        this.jwtUtils = jwtUtils;
    }

    public static class Config {}

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {

            ServerHttpRequest request = exchange.getRequest();
            String path = request.getURI().getPath();

            // Logica de la lista de exclusion
            boolean isExcluded = EXCLUDED_PATHS.stream().anyMatch(path::contains);

            if(isExcluded) {
                return chain.filter(exchange);
            }

            // 1. validar presencia del token
            if(!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, HttpStatus.UNAUTHORIZED);
            }

            String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if(authHeader == null || !authHeader.startsWith("Bearer ")) {
                return onError(exchange, HttpStatus.UNAUTHORIZED);
            }

            String token = authHeader.substring(7);

            // 2. Validar la firma del token
            if(jwtUtils.isInvalid(token)) {
                return onError(exchange, HttpStatus.UNAUTHORIZED);
            }

            // 3. Extraer info y pasarla a los microserviciospor headers
            var claims = jwtUtils.getClaims(token);

            // 4. Inyectar el usuario y sus empresas (multi-tenant)
            ServerHttpRequest modifiedRequest = request.mutate()
                    .header("X-User-Sub", claims.getSubject())
                    .header("X-User-Clients", claims.get("clientIds").toString())
                    .build();
            
            return chain.filter(exchange.mutate().request(modifiedRequest).build()); 
        };
    }

    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus status) {
        exchange.getResponse().setStatusCode(status);
        return exchange.getResponse().setComplete();
    }
    
}
