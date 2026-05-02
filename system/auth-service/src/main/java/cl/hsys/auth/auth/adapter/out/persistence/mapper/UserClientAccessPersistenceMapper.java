package cl.hsys.auth.auth.adapter.out.persistence.mapper;

import org.springframework.stereotype.Component;

import cl.hsys.auth.auth.adapter.out.persistence.entity.JpaUserClientAccess;
import cl.hsys.auth.auth.domain.UserClientAccess;

@Component
public class UserClientAccessPersistenceMapper {

    public UserClientAccess toDomain(JpaUserClientAccess jpaUserClientAccess) {
        if(jpaUserClientAccess == null) {
            return null;
        }
        return new UserClientAccess(
            jpaUserClientAccess.getId(),
            jpaUserClientAccess.getUserId(),
            jpaUserClientAccess.getUsername(),
            jpaUserClientAccess.getClientId(),
            jpaUserClientAccess.getClientRole(),
            jpaUserClientAccess.getClientName()
        );
    }

    public JpaUserClientAccess toEntity(UserClientAccess userClientAccess){
        if(userClientAccess == null) {
            return null;
        }
        System.out.println("username: " + userClientAccess.username());
        return JpaUserClientAccess.builder()
            .userId(userClientAccess.userId())
            .username(userClientAccess.username())
            .clientId(userClientAccess.clientId())
            .clientRole(userClientAccess.businessRole())
            .clientName(userClientAccess.companyName())
            .build();
    }{

    }

}
