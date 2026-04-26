package cl.hsys.auth.registration.dto;

import java.util.UUID;

public record MembershipCreateEvent (
    UUID userId,
    UUID clientId,
    String companyName,
    String businessRole
){
    
}
