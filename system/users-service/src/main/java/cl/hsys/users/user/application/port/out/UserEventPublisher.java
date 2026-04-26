package cl.hsys.users.user.application.port.out;

import cl.hsys.users.user.domain.event.UserCreateEvent;

public interface UserEventPublisher {
    
    void publishUserCreatedEvent(UserCreateEvent event);

}
