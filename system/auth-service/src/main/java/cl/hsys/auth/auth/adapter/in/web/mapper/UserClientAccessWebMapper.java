package cl.hsys.auth.auth.adapter.in.web.mapper;

import org.springframework.stereotype.Component;

import cl.hsys.auth.auth.application.command.CreateUserClientAccessCommand;
import cl.hsys.auth.registration.dto.MembershipCreateEvent;

@Component
public class UserClientAccessWebMapper {

    public CreateUserClientAccessCommand toCommand(
        MembershipCreateEvent event
    ) {
        return new CreateUserClientAccessCommand(
            event.userId(),
            event.clientId(),
            event.businessRole(),
            event.companyName()
        );
    }

}
