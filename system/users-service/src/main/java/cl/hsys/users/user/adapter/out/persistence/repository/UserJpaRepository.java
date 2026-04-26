package cl.hsys.users.user.adapter.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.hsys.users.user.adapter.out.persistence.entity.JpaUser;

@Repository
public interface UserJpaRepository extends JpaRepository<JpaUser, UUID>{

    boolean existsByUsername(String username);

    boolean existsByMail(String mail);
    
}
