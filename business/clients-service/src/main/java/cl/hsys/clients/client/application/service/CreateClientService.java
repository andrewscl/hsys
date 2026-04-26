package cl.hsys.clients.client.application.service;

import java.util.UUID;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.hsys.clients.client.application.command.CreateClientCommand;
import cl.hsys.clients.client.application.port.in.CreateClientCase;
import cl.hsys.clients.client.application.port.out.ClientRepositoryPort;
import cl.hsys.clients.client.domain.exceptions.ClientAlreadyExistsException;
import cl.hsys.clients.client.domain.model.Client;
import cl.hsys.clients.membership.adapter.out.messaging.events.MembershipCreateEvent;
import cl.hsys.clients.membership.application.command.CreateMembershipCommand;
import cl.hsys.clients.membership.application.port.in.CreateMembershipUseCase;
import cl.hsys.clients.membership.domain.enums.BusinessRole;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateClientService implements CreateClientCase {

    private final ApplicationEventPublisher applicationEventPublisher;
    private final ClientRepositoryPort clientRepositoryPort;
    private final CreateMembershipUseCase createMembershipUseCase;
    
    @Override
    @Transactional
    public UUID createClient (CreateClientCommand command) {

        // 1.- Validación de negocio
        if(clientRepositoryPort.existsByName(command.name())){
             throw new ClientAlreadyExistsException(
                    "Client with name " + command.name() + " already exists");
        }

        // 2.- Crear la entidad de dominio del cliente
        Client client = new Client(
            command.id(),
            command.ownerId(),
            command.name(),
            command.legalName(),
            command.taxId(),
            command.contactEmail(),
            command.contactPhone(),
            command.timezone(),
            true,
            null,
            null,
            command.ownerId().toString(),
            command.ownerId().toString()
        );

        // Persistir el cliente
        Client saved = clientRepositoryPort.save(client);

        // Crear entidad membership
        UUID newMembershipId = UUID.randomUUID();

        // 4.- Crear la membresia inicial (Vincula usuario y cliente)
        CreateMembershipCommand membershipCommand =
                                    new CreateMembershipCommand(
            newMembershipId,
            command.ownerId(),
            command.id(),
            BusinessRole.OWNER,
            true,
            command.ownerId().toString(),
            command.ownerId().toString()
        );

        // 5.- Ejecutar el caso de uso de membresia
        createMembershipUseCase.createMembership(membershipCommand);

        // Publicar el evento internamente
        MembershipCreateEvent event = new MembershipCreateEvent(
            command.ownerId(),
            command.id(),
            command.name(),
            BusinessRole.OWNER.name()
        );

        applicationEventPublisher.publishEvent(event);

        return saved.id();

    }

}
