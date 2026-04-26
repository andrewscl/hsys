package cl.hsys.clients.client.adapter.out.persistence;

import org.springframework.stereotype.Component;

import cl.hsys.clients.client.adapter.out.persistence.entity.JpaClient;
import cl.hsys.clients.client.adapter.out.persistence.mapper.ClientPersistenceMapper;
import cl.hsys.clients.client.adapter.out.persistence.repository.ClientJpaRepository;
import cl.hsys.clients.client.application.port.out.ClientRepositoryPort;
import cl.hsys.clients.client.domain.model.Client;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ClientPersistenceAdapter implements ClientRepositoryPort{

    private final ClientJpaRepository clientJpaRepository;
    private final ClientPersistenceMapper clientPersistenceMapper;


    @Override
    public Client save(Client client) {
        JpaClient jpaClient = clientPersistenceMapper.toEntity(client);
        JpaClient saved = clientJpaRepository.save(jpaClient);
        return clientPersistenceMapper.toDomain(saved);
    }

    @Override
    public Boolean existsByName(String name){
        return clientJpaRepository.existsByName(name);
    }

}
