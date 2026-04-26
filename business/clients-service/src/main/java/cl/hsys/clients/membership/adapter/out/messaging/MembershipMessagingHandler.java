package cl.hsys.clients.membership.adapter.out.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import cl.hsys.clients.membership.adapter.out.messaging.events.MembershipCreateEvent;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MembershipMessagingHandler {

    private final RabbitTemplate rabbitTemplate;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleMembershipCreated(MembershipCreateEvent event){

        rabbitTemplate.convertAndSend(
            "membership.exchange",
            "membership.create",
            event
        );

    }

}
