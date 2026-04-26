package cl.hsys.users.user.application.command;

import java.util.UUID;

public record CreateUserCommand (
    UUID userId,
    String username,
    String mail,
    String phone,
    String timezone
){}
