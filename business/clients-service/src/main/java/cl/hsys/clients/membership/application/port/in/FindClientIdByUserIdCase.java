package cl.hsys.clients.membership.application.port.in;

import java.util.List;
import java.util.UUID;

public interface FindClientIdByUserIdCase {

    List<UUID> findClientIdsByUserId(UUID userId);

}
