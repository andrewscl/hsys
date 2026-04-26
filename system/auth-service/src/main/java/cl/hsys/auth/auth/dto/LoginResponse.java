package cl.hsys.auth.auth.dto;

import java.util.List;
import java.util.UUID;

import cl.hsys.auth.auth.domain.enums.GlobalRole;

public record LoginResponse (

    String accessToken,
    String refreshToken,
    String tokenType, //Generalmente Bearer
    UUID userId,
    List<UUID> clientIds,
    String username,
    GlobalRole role

) {

    //Cnstructor compacto para valores por defecto
    public LoginResponse {
        if(tokenType == null) tokenType = "Bearer";
    }

}
