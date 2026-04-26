package cl.hsys.auth.auth.adapter.out.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cl.hsys.auth.auth.adapter.out.persistence.entity.JpaUserClientAccess;

@Repository
public interface UserClientAccessRepository extends
                                    JpaRepository<JpaUserClientAccess, UUID> {

    @Query("SELECT u.clientId FROM JpaUserClientAccess u WHERE u.userId = :userId")
    List<UUID> findAllClientIdsByUserId(@Param("userId") UUID userId);

    @Query("SELECT u FROM JpaUserClientAccess u WHERE u.userId = :userId AND u.clientId = :clientId")
    Optional<JpaUserClientAccess> findByUserIdAndClientId(
        UUID userId,
        UUID clientId
    );

    @Query("SELECT u FROM JpaUserClientAccess u WHERE u.userId = :userId")
    Page<JpaUserClientAccess> findAllUserClientAccessByUserId
        (@Param("userId") UUID userId, Pageable pageable);
    
}
