package cl.hsys.auth.auth.application.port.out;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cl.hsys.auth.auth.domain.UserClientAccess;

public interface UserClientAccessRepositoryPort {

    UserClientAccess save(UserClientAccess userClientAccess);

    Page<UserClientAccess> findAll(Pageable pageable);

    Page<UserClientAccess> findAllUserClientAccessByUserId
        (UUID UserId, Pageable pageable);

    Optional<UserClientAccess> findByUserIdAndClientId(
        UUID userId,
        UUID clientId
    );



}
