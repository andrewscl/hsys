package cl.hsys.users.user.application.port.in;

import java.util.UUID;

import cl.hsys.users.user.application.command.CreateUserCommand;

public interface CreateUserCase {

    UUID createUser (CreateUserCommand request);

}
