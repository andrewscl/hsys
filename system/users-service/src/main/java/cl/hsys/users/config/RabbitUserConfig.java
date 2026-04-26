package cl.hsys.users.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitUserConfig {
    
    // 1. Definimos el nombre del Exchange (DEBE ser el mismo que usa Auth)
    public static final String EXCHANGE_NAME = "user.exchange";
    
    // 2. Definimos el nombre de LA COLA de este micro
    public static final String USERS_QUEUE = "users.registration.queue";

    // 3. Definimos la "Routing Key" (La etiqueta del mensaje)
    public static final String ROUTING_KEY = "user.created";

    @Bean
    public Queue usersQueue() {
        // durable = true para que no se pierda si se reinicia el server
        return new Queue(USERS_QUEUE, true);
    }

    @Bean
    public TopicExchange userExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding bindingUsers(Queue usersQueue, TopicExchange userExchange) {
        // "Une esta cola a este exchange cuando el mensaje venga marcado como 'user.created'"
        return BindingBuilder.bind(usersQueue)
                .to(userExchange)
                .with(ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    
}
