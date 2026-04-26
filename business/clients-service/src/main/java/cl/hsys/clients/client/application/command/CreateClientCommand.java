package cl.hsys.clients.client.application.command;

import java.util.UUID;

public record CreateClientCommand (
    UUID id,
    UUID ownerId,
    String name,
    String legalName,
    String taxId,
    String contactEmail,
    String contactPhone,
    String timezone
) {}
