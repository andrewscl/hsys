package cl.hsys.clients.client.adapter.out.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import cl.hsys.clients.client.application.port.out.ClientEventPublisher;
import cl.hsys.clients.client.domain.event.ClientCreateEvent;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RabbitClientEventAdapter implements ClientEventPublisher{

    private final RabbitTemplate rabbitTemplate;

    //Nombre que deben coincidor en el RabbitConfig
    private static final String EXCHANGE_NAME = "user.exchange";
    private static final String ROUTING_KEY = "user.created";

    @Override
    public void publishClientCreatedEvent(ClientCreateEvent event) {
        // Publicar el evento en RabbitMQ
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, event);
    }

}
