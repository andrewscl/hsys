package cl.hsys.clients.client.adapter.in.web;


import java.util.UUID;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import cl.hsys.clients.client.adapter.in.web.dto.UserRegisteredEvent;
import cl.hsys.clients.client.adapter.in.web.mapper.ClientWebMapper;
import cl.hsys.clients.client.application.command.CreateClientCommand;
import cl.hsys.clients.client.application.port.in.CreateClientCase;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ClientEventConsumer {

    private final ClientWebMapper clientWebMapper;
    private final CreateClientCase createClientCase;

    @RabbitListener(queues = "clients.registration.queue")
    public void handleClientCreated(UserRegisteredEvent event) {

        //Crear nuevo ID Client
        UUID newClientId = UUID.randomUUID();

        CreateClientCommand command = 
                    clientWebMapper.toCommand(event, newClientId);

        createClientCase.createClient(command);
    }

}
