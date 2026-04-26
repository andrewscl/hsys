package cl.hsys.auth.registration.dto;

import java.io.Serializable;
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
) implements Serializable {}
