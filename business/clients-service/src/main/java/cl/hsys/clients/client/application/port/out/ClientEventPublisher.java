package cl.hsys.clients.client.application.port.out;

import cl.hsys.clients.client.domain.event.ClientCreateEvent;

public interface ClientEventPublisher {

    void publishClientCreatedEvent(ClientCreateEvent event);
    
}
