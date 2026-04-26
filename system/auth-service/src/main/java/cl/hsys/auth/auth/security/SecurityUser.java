package cl.hsys.auth.auth.security;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import cl.hsys.auth.auth.dto.UserInternalDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class SecurityUser implements UserDetails {

    @Getter
    private final UserInternalDto userInternalDto;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(userInternalDto.role() == null) {
            return Collections.emptyList();
        }
        return List.of(new SimpleGrantedAuthority(
                                        userInternalDto.role().name()));
    }

    @Override
    public String getPassword() {
        return userInternalDto.password();
    }

    @Override
    public String getUsername() {
        return userInternalDto.username();
    }

    public UUID getUserId() {
        return userInternalDto.id();
    }

    public List<UUID> getClientIds() {
        return userInternalDto.clientIds();
    }

}
