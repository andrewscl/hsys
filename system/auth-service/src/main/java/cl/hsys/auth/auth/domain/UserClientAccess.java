package cl.hsys.auth.auth.domain;

import java.util.UUID;

public record UserClientAccess (
    UUID id,
    UUID userId,
    UUID clientId,
    String businessRole,
    String companyName
){}
