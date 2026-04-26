package cl.hsys.auth.auth.dto;

import java.util.List;
import java.util.UUID;

import cl.hsys.auth.auth.domain.enums.GlobalRole;

public record UserInternalDto (

    UUID id,
    String username,
    String password,
    String mail,
    List<UUID> clientIds,
    GlobalRole role
){
    
}
