package cl.hsys.auth.auth.adapter.out.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.hsys.auth.auth.adapter.out.persistence.entity.JpaUser;

@Repository
public interface AuthUserRepository extends JpaRepository<JpaUser, UUID>{

    Optional<JpaUser> findByUsername(String username);

}
