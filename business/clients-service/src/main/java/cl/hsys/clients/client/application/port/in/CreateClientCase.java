package cl.hsys.clients.client.application.port.in;

import java.util.UUID;

import cl.hsys.clients.client.application.command.CreateClientCommand;

public interface CreateClientCase {

    UUID createClient (CreateClientCommand request);

}
