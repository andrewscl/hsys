package cl.hsys.auth.auth.application.port.in;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cl.hsys.auth.auth.domain.UserClientAccess;

public interface UserClientAccessQueryCase {

    Page<UserClientAccess> findAllUserClientAccess
            (Pageable pageable);

    Page<UserClientAccess> findAllUserClientAccessByUserId
            (UUID userId, Pageable pageable);

    Optional<UserClientAccess> findByUserIdAndClientId
            (UUID userId, UUID clientId);

}
