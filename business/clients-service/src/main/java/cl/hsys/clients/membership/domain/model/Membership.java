package cl.hsys.clients.membership.domain.model;

import java.time.Instant;
import java.util.UUID;

import cl.hsys.clients.membership.domain.enums.BusinessRole;

public record Membership (
    UUID id,
    UUID userId,
    UUID clientId,
    BusinessRole role,
    Boolean active,
    Instant createdAt,
    Instant updatedAt,
    String createdBy,
    String updatedBy
){}
