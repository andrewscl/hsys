package cl.hsys.auth.auth.application.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.hsys.auth.auth.application.port.in.UserClientAccessQueryCase;
import cl.hsys.auth.auth.application.port.out.UserClientAccessRepositoryPort;
import cl.hsys.auth.auth.domain.UserClientAccess;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserClientAccessQueryService
        implements UserClientAccessQueryCase {

    private final UserClientAccessRepositoryPort repositoryPort;

    @Override
    @Transactional(readOnly = true)
    public Page<UserClientAccess> findAllUserClientAccess
            (Pageable pageable){
        return repositoryPort.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserClientAccess> findAllUserClientAccessByUserId
            (UUID userId, Pageable pageable) {
        return repositoryPort
                .findAllUserClientAccessByUserId(userId, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserClientAccess> findByUserIdAndClientId
                        (UUID userId, UUID clientId) {
        return repositoryPort.findByUserIdAndClientId(
                                            userId, clientId);
    }

}
