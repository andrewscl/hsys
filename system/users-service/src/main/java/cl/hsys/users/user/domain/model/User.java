package cl.hsys.users.user.domain.model;

import java.time.Instant;
import java.util.UUID;

public record User (
    UUID id,
    String username,
    String mail,
    String phone,
    Boolean active,
    Instant createdAt,
    Instant updatedAt,
    String createdBy,
    String updatedBy
){}