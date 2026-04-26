package cl.hsys.users.user.adapter.out.persistence.mapper;

import cl.hsys.users.user.adapter.out.persistence.entity.JpaUser;
import cl.hsys.users.user.domain.model.User;

public final class UserPersistenceMapper {
    
    public static User toDomain (JpaUser jpaUser) {
        if (jpaUser == null) {
            return null;
        }
        return new User(
            jpaUser.getId(),
            jpaUser.getUsername(),
            jpaUser.getMail(),
            jpaUser.getPhone(),
            jpaUser.getActive(),
            jpaUser.getCreatedAt(),
            jpaUser.getUpdatedAt(),
            jpaUser.getCreatedBy(),
            jpaUser.getUpdatedBy()
        );
    }

    public static JpaUser toEntity (User user){
        if (user == null) {
            return null;
        }
        return JpaUser.builder()
            .id(user.id())
            .username(user.username())
            .mail(user.mail())
            .phone(user.phone())
            .active(user.active())
            .createdBy(user.username())
            .updatedBy(user.username())
            .build();
    }

}
