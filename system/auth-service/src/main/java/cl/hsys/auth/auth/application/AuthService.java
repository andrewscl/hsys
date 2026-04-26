package cl.hsys.auth.auth.application;

import java.util.List;
import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import cl.hsys.auth.auth.domain.enums.GlobalRole;
import cl.hsys.auth.auth.dto.LoginRequest;
import cl.hsys.auth.auth.dto.LoginResponse;
import cl.hsys.auth.auth.security.JwtTokenProvider;
import cl.hsys.auth.auth.security.SecurityUser;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginResponse authenticate (LoginRequest req) {

        // Intentar autenticar (Spring Security lanza excepcion si falla)
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(req.username(), req.password())
        );

        // Establecer contexto
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Extraer el SecurityUser personalizado del objeto authentication
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        UUID userId = securityUser.getUserId();
        GlobalRole role = securityUser.getUserInternalDto().role();
        List<UUID> clientIds = securityUser.getClientIds();

        // Generar tokens
        String accessToken = jwtTokenProvider.generateAccessToken(authentication, userId, clientIds);
        String refreshToken = jwtTokenProvider.generateRefreshToken(req.username());

        return new LoginResponse(
            accessToken,
            refreshToken,
            "Bearer",
            userId,
            clientIds,
            req.username(),
            role
        );
    }
}
