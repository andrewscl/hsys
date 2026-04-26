package cl.hsys.users.user.adapter.in.web.dto;

import java.time.Instant;
import java.util.UUID;

public record UserResponse (
    UUID id,
    String username,
    String mail,
    String phone,
    Boolean active,
    Instant createdAt,
    Instant updatedAt
){}
