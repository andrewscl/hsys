package cl.hsys.clients.membership.adapter.out.persistence.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.hsys.clients.membership.adapter.out.persistence.entity.JpaMembership;

@Repository
public interface MembershipJpaRepository extends JpaRepository<JpaMembership, UUID> {

    List<JpaMembership> findAllByUserId (UUID userId);

}
