package cl.hsys.clients.client.adapter.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.hsys.clients.client.adapter.out.persistence.entity.JpaClient;

@Repository
public interface ClientJpaRepository extends JpaRepository<JpaClient, UUID>{

    boolean existsByName(String name);

}
