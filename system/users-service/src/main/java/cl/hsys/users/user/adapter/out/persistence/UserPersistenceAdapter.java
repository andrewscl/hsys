package cl.hsys.users.user.adapter.out.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import cl.hsys.users.user.adapter.out.persistence.entity.JpaUser;
import cl.hsys.users.user.adapter.out.persistence.mapper.UserPersistenceMapper;
import cl.hsys.users.user.adapter.out.persistence.repository.UserJpaRepository;
import cl.hsys.users.user.application.port.out.UserRepositoryPort;
import cl.hsys.users.user.domain.model.User;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserRepositoryPort {
    
    private final UserJpaRepository userJpaRepository;

    @Override
    public User save(User user) {
        JpaUser jpaUser = UserPersistenceMapper.toEntity(user);
        JpaUser saved = userJpaRepository.save(jpaUser);
        return UserPersistenceMapper.toDomain(saved);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userJpaRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByMail(String mail) {
        return userJpaRepository.existsByMail(mail);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userJpaRepository.findById(id).map(UserPersistenceMapper::toDomain);
    }

}
