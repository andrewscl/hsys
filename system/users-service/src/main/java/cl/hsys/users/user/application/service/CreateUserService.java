package cl.hsys.users.user.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import cl.hsys.users.user.application.command.CreateUserCommand;
import cl.hsys.users.user.application.port.in.CreateUserCase;
import cl.hsys.users.user.application.port.out.UserRepositoryPort;
import cl.hsys.users.user.domain.exception.UserAlreadyExistsException;
import cl.hsys.users.user.domain.model.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateUserService implements CreateUserCase {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public UUID createUser (CreateUserCommand command) {

        if(userRepositoryPort.existsByUsername(command.username())){
            throw new UserAlreadyExistsException("Username already exists");
        }

        if(userRepositoryPort.existsByMail(command.mail())){
            throw new UserAlreadyExistsException("Mail already exists");
        }

        User user = new User(
                command.userId(),
                command.username(),
                command.mail(),
                command.phone(),
                true,
                null,
                null,
                command.username(),
                command.username()
        );

        User saved = userRepositoryPort.save(user);

        return saved.id();
    }
    
}
