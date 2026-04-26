package cl.hsys.clients.membership.adapter.in.web.mapper;

import java.util.UUID;

import org.springframework.stereotype.Component;

import cl.hsys.clients.membership.application.command.CreateMembershipCommand;
import cl.hsys.clients.membership.domain.enums.BusinessRole;

@Component
public class MembershipWebMapper {
    
    public CreateMembershipCommand toCommand(
                    UUID membershipId,
                    UUID userId,
                    UUID clientId,
                    String userSystem) {

        return new CreateMembershipCommand(
            membershipId,
            userId,
            clientId,
            BusinessRole.OWNER,
            true,
            userSystem,
            userSystem);
    }


}
