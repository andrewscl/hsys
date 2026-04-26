package cl.hsys.clients.membership.application.port.in;

import cl.hsys.clients.membership.application.command.CreateMembershipCommand;
import cl.hsys.clients.membership.domain.model.Membership;

public interface CreateMembershipUseCase {
    
    Membership createMembership(CreateMembershipCommand command);

}
