package cl.hsys.users.user.application.port.out;

import java.util.Optional;
import java.util.UUID;

import cl.hsys.users.user.domain.model.User;

public interface UserRepositoryPort {

    User save(User user);

    Boolean existsByUsername(String username);

    Boolean existsByMail(String mail);

    Optional<User> findById(UUID id);

}
