package cl.hsys.auth.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    // --- Configuración de salida (Auth -> Clients) ---
    public static final String USER_EXCHANGE = "user.exchange";
    public static final String USER_CREATED_ROUTING_KEY = "user.created";

    // --- Configuración de entrada (Clients -> Auth) ---
    public static final String MEMBERSHIP_EXCHANGE = "membership.exchange";
    public static final String AUTH_MEMBERSHIP_QUEUE = "auth.membership.queue";
    public static final String MEMBERSHIP_CREATED_ROUTING_KEY = "membership.create";

    // Exchange de salida
    @Bean
    public TopicExchange userExchange() {
        // TopicExchange es más flexible que DirectExchange para el futuro
        return new TopicExchange(USER_EXCHANGE);
    }

    // Exchange de entrada
    @Bean
    public TopicExchange membershipExchange() {
        return new TopicExchange(MEMBERSHIP_EXCHANGE);
    }

    // Cola donde Auth escuchará eventos de Membership
    @Bean
    public Queue authMembershipQueue() {
        return new Queue(AUTH_MEMBERSHIP_QUEUE, true);
    }

    // El puente (Binding) que une el Exchange con la cola de Auth
    @Bean
    public Binding bindingMembershipCreated(
                        Queue AuthMembershipQueue, TopicExchange membershipExchange) {
        return BindingBuilder
                        .bind(AuthMembershipQueue)
                        .to(membershipExchange)
                        .with(MEMBERSHIP_CREATED_ROUTING_KEY);
    }

    // Configuración del Template y Converter
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        // IMPORTANTE: Esto convierte tus Records/Objetos a JSON automáticamente
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
