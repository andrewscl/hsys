package cl.hsys.users.user.domain.event;

import java.util.UUID;

public record UserCreateEvent (
    UUID userId,
    String username
){}
