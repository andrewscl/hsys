package cl.hsys.clients.membership.adapter.out.persistence.mapper;

import org.springframework.stereotype.Component;

import cl.hsys.clients.client.adapter.out.persistence.entity.JpaClient;
import cl.hsys.clients.membership.adapter.out.persistence.entity.JpaMembership;
import cl.hsys.clients.membership.domain.model.Membership;

@Component
public class MembershipPersistenceMapper {

    public Membership toDomain (JpaMembership jpaMembership) {
        if(jpaMembership == null) {
            return null;
        }
        return new Membership(
            jpaMembership.getId(),
            jpaMembership.getUserId(),
            jpaMembership.getClient().getId(),
            jpaMembership.getRole(),
            jpaMembership.getActive(),
            jpaMembership.getCreatedAt(),
            jpaMembership.getUpdatedAt(),
            jpaMembership.getCreatedBy(),
            jpaMembership.getUpdatedBy()
        );
    }

    public JpaMembership toEntity (Membership membership, JpaClient jpaClient) {
        if(membership == null){
            return null;
        }
        return JpaMembership.builder()
            .id(membership.id())
            .userId(membership.userId())
            .client(jpaClient)
            .role(membership.role())
            .active(membership.active())
            .createdBy(membership.createdBy() != null ? membership.createdBy() : "SYSTEM_REGISTRATION")
            .updatedBy(membership.updatedBy() != null ? membership.updatedBy() : "SYSTEM_REGISTRATION")
            .build();
    }
    
}
