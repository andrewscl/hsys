package cl.hsys.clients.membership.adapter.out.persistence;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import cl.hsys.clients.client.adapter.out.persistence.entity.JpaClient;
import cl.hsys.clients.client.adapter.out.persistence.repository.ClientJpaRepository;
import cl.hsys.clients.membership.adapter.out.persistence.entity.JpaMembership;
import cl.hsys.clients.membership.adapter.out.persistence.mapper.MembershipPersistenceMapper;
import cl.hsys.clients.membership.adapter.out.persistence.repository.MembershipJpaRepository;
import cl.hsys.clients.membership.application.port.out.MembershipRepositoryPort;
import cl.hsys.clients.membership.domain.model.Membership;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MembershipPersistenceAdapter implements MembershipRepositoryPort {

    private final MembershipJpaRepository membershipJpaRepository;
    private final ClientJpaRepository clientJpaRepository;
    private final MembershipPersistenceMapper membershipPersistenceMapper;

    @Override
    public Membership save (Membership membership) {
        //Obtenemos una referencia al JpaClient usando el ID que viene en el dominio.
        //geoReferencedById no hace una consulta SQL inmediata, es muy eficiente.
        JpaClient jpaClient = clientJpaRepository.getReferenceById(membership.clientId());

        //Convertir el dominio + la entidad persistente del cliente a la entidad membresia
        JpaMembership jpaMembership = membershipPersistenceMapper.toEntity(membership, jpaClient);

        //Guarda en la base de datos
        JpaMembership saved = membershipJpaRepository.save(jpaMembership);
        
        //Devuelve el dominio (Mapping salida)
        return membershipPersistenceMapper.toDomain(saved);
    }

    @Override
    public List<UUID> findClientIdsByUserId(UUID userId) {
        return membershipJpaRepository.findAllByUserId(userId)
                .stream()
                .map(jpaMembership -> jpaMembership.getClient().getId())
                .toList();
    }

}
