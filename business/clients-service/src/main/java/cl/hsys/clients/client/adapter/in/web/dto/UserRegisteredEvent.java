package cl.hsys.clients.client.adapter.in.web.dto;

import java.util.UUID;

public record UserRegisteredEvent (
    UUID userId,
    String username,
    String email,
    String phone,
    String companyName,
    String taxId,
    String timezone,
    String verificationToken
){}
