package cl.hsys.auth.auth.security;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cl.hsys.auth.auth.adapter.out.persistence.entity.JpaUser;
import cl.hsys.auth.auth.adapter.out.persistence.repository.AuthUserRepository;
import cl.hsys.auth.auth.adapter.out.persistence.repository.UserClientAccessRepository;
import cl.hsys.auth.auth.dto.UserInternalDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AuthUserRepository authUserRepository;
    private final UserClientAccessRepository userClientsAccessRepository;

    @Override
    public UserDetails loadUserByUsername (String username)
            throws UsernameNotFoundException {

        JpaUser user = authUserRepository.findByUsername(username)
                .orElseThrow(
                    () -> new UsernameNotFoundException("Usuario no encontrado")
                );

        List<UUID> clientIds = userClientsAccessRepository.findAllClientIdsByUserId(user.getId());

        UserInternalDto userInternalDto = new UserInternalDto(
            user.getId(),
            user.getUsername(),
            user.getPassword(),
            user.getMail(),
            clientIds,
            user.getRole()
        );

        // Pasa el password hash a Spring Security
        return new SecurityUser(userInternalDto);

    }

}
