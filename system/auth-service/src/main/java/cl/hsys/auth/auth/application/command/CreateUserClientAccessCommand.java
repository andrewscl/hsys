package cl.hsys.auth.auth.application.command;

import java.util.UUID;

public record CreateUserClientAccessCommand (
    UUID userId,
    String username,
    UUID clientId,
    String businessRole,
    String companyName
){
    
}
