package cl.hsys.auth.registration.api;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import cl.hsys.auth.auth.adapter.in.web.mapper.UserClientAccessWebMapper;
import cl.hsys.auth.auth.application.command.CreateUserClientAccessCommand;
import cl.hsys.auth.auth.application.port.in.UserClientAccessCommandCase;
import cl.hsys.auth.registration.dto.MembershipCreateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class MembershipEventConsumer {

    private final UserClientAccessWebMapper mapper;
    private final UserClientAccessCommandCase createCase;

    @RabbitListener(queues = "auth.membership.queue")
    public void handleMembershipCreated(MembershipCreateEvent event){
        log.info("Procesando acceso de usuario");

        CreateUserClientAccessCommand command =
                mapper.toCommand(event);

        createCase.createUserClientAccess(command);
    }

}
