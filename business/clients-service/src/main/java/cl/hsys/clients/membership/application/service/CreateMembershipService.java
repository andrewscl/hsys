package cl.hsys.clients.membership.application.service;

import org.springframework.stereotype.Service;

import cl.hsys.clients.membership.application.command.CreateMembershipCommand;
import cl.hsys.clients.membership.application.port.in.CreateMembershipUseCase;
import cl.hsys.clients.membership.application.port.out.MembershipRepositoryPort;
import cl.hsys.clients.membership.domain.model.Membership;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateMembershipService implements CreateMembershipUseCase {

    private final MembershipRepositoryPort membershipRepositoryPort;

    @Override
    public Membership createMembership(CreateMembershipCommand command) {
        
        Membership membership = new Membership(
            command.id(),
            command.userId(),
            command.clientId(),
            command.role(),
            true,
            null,
            null,
            "SYSTEM_BOOTSTRAP",
            "SYSTEM_BOOTSTRAP"
        );

        Membership saved = membershipRepositoryPort.save(membership);

        return saved;

    }

}
