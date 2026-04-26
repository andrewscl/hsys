package cl.hsys.users.user.application.port.out.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import cl.hsys.users.user.application.port.out.UserEventPublisher;
import cl.hsys.users.user.domain.event.UserCreateEvent;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RabbitUserEventAdapter implements UserEventPublisher{

    private final RabbitTemplate rabbitTemplate;

    //Nombre que deben coincidor en el RabbitConfig
    private static final String EXCHANGE_NAME = "user.exchange";
    private static final String ROUTING_KEY = "user.created";

    @Override
    public void publishUserCreatedEvent(UserCreateEvent event) {
        // Publicar el evento en RabbitMQ
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, event);
    }

}
