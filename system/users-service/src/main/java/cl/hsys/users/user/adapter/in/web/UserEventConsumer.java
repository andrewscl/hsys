package cl.hsys.users.user.adapter.in.web;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import cl.hsys.users.user.adapter.in.web.dto.UserRegisteredEvent;
import cl.hsys.users.user.adapter.in.web.mapper.UserWebMapper;
import cl.hsys.users.user.application.command.CreateUserCommand;
import cl.hsys.users.user.application.port.in.CreateUserCase;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserEventConsumer {

    private final UserWebMapper userWebMapper;
    private final CreateUserCase createUserCase;

    @RabbitListener(queues = "users.registration.queue")
    public void handleUserCreated(UserRegisteredEvent event) {

        CreateUserCommand command = userWebMapper.toCommand(event);

        createUserCase.createUser(command);
    }

}
