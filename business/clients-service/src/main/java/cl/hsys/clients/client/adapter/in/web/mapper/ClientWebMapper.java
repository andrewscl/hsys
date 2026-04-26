package cl.hsys.clients.client.adapter.in.web.mapper;

import java.util.UUID;

import org.springframework.stereotype.Component;

import cl.hsys.clients.client.adapter.in.web.dto.UserRegisteredEvent;
import cl.hsys.clients.client.application.command.CreateClientCommand;

@Component
public class ClientWebMapper {

    public CreateClientCommand toCommand
                        (UserRegisteredEvent event, UUID newClientId) {
        return new CreateClientCommand(
            newClientId,
            event.userId(),
            event.companyName(),
            event.companyName(),
            event.taxId(),
            event.email(),
            event.phone(),
            event.timezone()
        );
    }

}
