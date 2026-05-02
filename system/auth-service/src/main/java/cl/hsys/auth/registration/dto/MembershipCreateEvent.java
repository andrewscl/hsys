package cl.hsys.auth.registration.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MembershipCreateEvent (
    UUID userId,
    @JsonProperty("username")
    String username,
    UUID clientId,
    String companyName,
    String businessRole
){
    
}
