package cl.hsys.clients.client.application.port.out;

import cl.hsys.clients.client.domain.model.Client;

public interface ClientRepositoryPort {

    Client save(Client client);

    Boolean existsByName(String name);

}
