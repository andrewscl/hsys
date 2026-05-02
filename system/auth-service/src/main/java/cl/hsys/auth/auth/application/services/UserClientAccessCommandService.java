package cl.hsys.auth.auth.application.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.hsys.auth.auth.application.command.CreateUserClientAccessCommand;
import cl.hsys.auth.auth.application.port.in.UserClientAccessCommandCase;
import cl.hsys.auth.auth.application.port.out.UserClientAccessRepositoryPort;
import cl.hsys.auth.auth.domain.UserClientAccess;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserClientAccessCommandService
                implements UserClientAccessCommandCase{

    private final UserClientAccessRepositoryPort repositoryPort;

    @Override
    @Transactional
    public UserClientAccess createUserClientAccess(
                CreateUserClientAccessCommand command
    ) {

        return repositoryPort.findByUserIdAndClientId(
            command.userId(), command.clientId())

        .orElseGet(() -> {
            UserClientAccess userClientAccess = 
                new UserClientAccess(
                    null,
                    command.userId(),
                    command.username(),
                    command.clientId(),
                    command.businessRole(),
                    command.companyName()
                );

            UserClientAccess saved =
                            repositoryPort.save(userClientAccess);
            return saved;
        });

    }

}