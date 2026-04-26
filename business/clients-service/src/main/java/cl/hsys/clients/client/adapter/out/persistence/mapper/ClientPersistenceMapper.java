package cl.hsys.clients.client.adapter.out.persistence.mapper;

import org.springframework.stereotype.Component;

import cl.hsys.clients.client.adapter.out.persistence.entity.JpaClient;
import cl.hsys.clients.client.domain.model.Client;

@Component
public final class ClientPersistenceMapper {
    
    public Client toDomain (JpaClient jpaClient) {
        if(jpaClient == null) {
            return null;
        }
        return new Client(
            jpaClient.getId(),
            jpaClient.getOwnerId(),
            jpaClient.getName(),
            jpaClient.getLegalName(),
            jpaClient.getTaxId(),
            jpaClient.getContactEmail(),
            jpaClient.getContactPhone(),
            jpaClient.getTimezone(),
            jpaClient.getActive(),
            jpaClient.getCreatedAt(),
            jpaClient.getUpdatedAt(),
            jpaClient.getCreatedBy(),
            jpaClient.getUpdatedBy()
        );
    }

    public JpaClient toEntity (Client client) {
        if (client == null) {
            return null;
        }
        return JpaClient.builder()
            .id(client.id())
            .ownerId(client.ownerId())
            .name(client.name())
            .legalName(client.legalName())
            .taxId(client.taxId())
            .contactEmail(client.contactEmail())
            .contactPhone(client.contactPhone())
            .timezone(client.timezone())
            .active(client.active())
            .createdBy(client.createdBy() != null ? client.createdBy() : "SYSTEM_REGISTRATION")
            .updatedBy(client.updatedBy() != null ? client.updatedBy() : "SYSTEM_REGISTRATION")
            .build();
    }

}
