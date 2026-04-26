package cl.hsys.clients.membership.adapter.out.messaging.events;

import java.util.UUID;

public record MembershipCreateEvent (
    UUID userId,
    UUID clientId,
    String companyName,
    String businessRole
){
    
}
