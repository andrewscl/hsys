package cl.hsys.users.user.application.port.in;

import java.util.UUID;

import cl.hsys.users.user.domain.model.User;

public interface GetUserCase {

    User getById (UUID id);

}
