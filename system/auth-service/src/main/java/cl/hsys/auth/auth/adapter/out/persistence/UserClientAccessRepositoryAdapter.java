package cl.hsys.auth.auth.adapter.out.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cl.hsys.auth.auth.adapter.out.persistence.entity.JpaUserClientAccess;
import cl.hsys.auth.auth.adapter.out.persistence.mapper.UserClientAccessPersistenceMapper;
import cl.hsys.auth.auth.adapter.out.persistence.repository.UserClientAccessRepository;
import cl.hsys.auth.auth.application.port.out.UserClientAccessRepositoryPort;
import cl.hsys.auth.auth.domain.UserClientAccess;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserClientAccessRepositoryAdapter 
                        implements UserClientAccessRepositoryPort {

    private final UserClientAccessRepository repository;
    private final UserClientAccessPersistenceMapper mapper;

    @Override
    @Transactional
    public UserClientAccess save(
                    UserClientAccess userClientAccess){

        JpaUserClientAccess jpaUserClientAccess =
                mapper.toEntity(userClientAccess);

        JpaUserClientAccess saved =
                repository.save(jpaUserClientAccess);

        return mapper.toDomain(saved);

    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserClientAccess> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserClientAccess> findAllUserClientAccessByUserId
        (UUID userId, Pageable pageable) {
        return repository.findAllUserClientAccessByUserId(userId, pageable)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<UserClientAccess>  findByUserIdAndClientId(
        UUID userId,
        UUID clientId
    ) {
        return repository.findByUserIdAndClientId(userId, clientId)
                .map(mapper::toDomain);
    }

}
