package cl.hsys.clients.membership.application.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.hsys.clients.membership.application.port.in.FindClientIdByUserIdCase;
import cl.hsys.clients.membership.application.port.out.MembershipRepositoryPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FindClientIdByUserService implements FindClientIdByUserIdCase {

    private final MembershipRepositoryPort membershipRepositoryPort;

    @Override
    @Transactional(readOnly = true)
    public List<UUID> findClientIdsByUserId(UUID userId) {
        return membershipRepositoryPort.findClientIdsByUserId(userId);
    }

}
