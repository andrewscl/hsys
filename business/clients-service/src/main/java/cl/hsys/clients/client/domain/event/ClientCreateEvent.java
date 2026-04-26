package cl.hsys.clients.client.domain.event;

import java.util.UUID;

public record ClientCreateEvent (
    UUID clientId,
    UUID ownerId,
    String name
){

}
