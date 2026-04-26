package cl.hsys.auth.registration.dto;

import java.util.UUID;

public record RegisterResponse (
    UUID userId,
    String status
){}

