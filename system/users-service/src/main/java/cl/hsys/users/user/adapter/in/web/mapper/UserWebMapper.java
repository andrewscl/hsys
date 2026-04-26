package cl.hsys.users.user.adapter.in.web.mapper;

import org.springframework.stereotype.Component;

import cl.hsys.users.user.adapter.in.web.dto.UserRegisteredEvent;
import cl.hsys.users.user.adapter.in.web.dto.UserResponse;
import cl.hsys.users.user.application.command.CreateUserCommand;
import cl.hsys.users.user.domain.model.User;

@Component
public class UserWebMapper {

    public CreateUserCommand toCommand(UserRegisteredEvent event) {
        return new CreateUserCommand(
            event.userId(),
            event.username(),
            event.email(),
            event.phone(),
            event.timezone()
        );
    }

    public UserResponse toResponse(User user) {
        return new UserResponse(
            user.id(),
            user.username(),
            user.mail(),
            user.phone(),
            user.active(),
            user.createdAt(),
            user.updatedAt()
        );
    }

}
