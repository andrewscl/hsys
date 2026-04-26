package cl.hsys.clients.membership.application.command;

import java.util.UUID;

import cl.hsys.clients.membership.domain.enums.BusinessRole;

public record CreateMembershipCommand (
    UUID id,
    UUID userId,
    UUID clientId,
    BusinessRole role,
    Boolean active,
    String createdBy,
    String updatedBy
){

}
