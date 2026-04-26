package cl.hsys.auth.auth.application.port.in;

import cl.hsys.auth.auth.application.command.CreateUserClientAccessCommand;
import cl.hsys.auth.auth.domain.UserClientAccess;

public interface UserClientAccessCommandCase {

    UserClientAccess createUserClientAccess(
                    CreateUserClientAccessCommand command);
    
}
