package cl.hsys.clients.client.domain.model;

import java.time.Instant;
import java.util.UUID;

public record Client (
    UUID id,
    UUID ownerId,
    String name,
    String legalName,
    String taxId,
    String contactEmail,
    String contactPhone,
    String timezone,
    Boolean active,
    Instant createdAt,
    Instant updatedAt,
    String createdBy,
    String updatedBy
){}
