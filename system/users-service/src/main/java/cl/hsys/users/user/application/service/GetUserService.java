package cl.hsys.users.user.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import cl.hsys.users.user.application.port.in.GetUserCase;
import cl.hsys.users.user.application.port.out.UserRepositoryPort;
import cl.hsys.users.user.domain.exception.UserNotFoundException;
import cl.hsys.users.user.domain.model.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetUserService implements GetUserCase {

    private final UserRepositoryPort userRepositoryPort;
    
    @Override
    public User getById (UUID id) {
        return userRepositoryPort.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

}
