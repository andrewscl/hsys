package cl.hsys.auth.auth.application.command;

import java.util.UUID;

public record CreateUserClientAccessCommand (
    UUID userId,
    UUID clientId,
    String businessRole,
    String companyName
){
    
}
