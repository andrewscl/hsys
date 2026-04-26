package cl.hsys.clients.membership.application.port.out;

import java.util.List;
import java.util.UUID;

import cl.hsys.clients.membership.domain.model.Membership;

public interface MembershipRepositoryPort {

    Membership save(Membership membership);

    List<UUID> findClientIdsByUserId(UUID userId);

}
