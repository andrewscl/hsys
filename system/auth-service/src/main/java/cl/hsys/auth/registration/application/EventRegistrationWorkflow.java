package cl.hsys.auth.registration.application;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cl.hsys.auth.auth.adapter.out.persistence.entity.JpaUser;
import cl.hsys.auth.auth.adapter.out.persistence.repository.AuthUserRepository;
import cl.hsys.auth.auth.domain.enums.GlobalRole;
import cl.hsys.auth.registration.dto.RegisterRequest;
import cl.hsys.auth.registration.dto.RegisterResponse;
import cl.hsys.auth.registration.dto.UserRegisteredEvent;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Primary
@RequiredArgsConstructor
public class EventRegistrationWorkflow implements RegistrationWorkflow {

    private final RabbitTemplate rabbitTemplate;
    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public RegisterResponse register(RegisterRequest req) {

        //Generar identidad global y token de verificacion
        UUID newUserId = UUID.randomUUID();
        String verificationToken = UUID.randomUUID().toString();

        /*
        Persitencia local
        Agregamos token y estado enabled = false
        */
        JpaUser newUser = JpaUser.builder()
            .id(newUserId)
            .username(req.username())
            .mail(req.mail())
            .password(passwordEncoder.encode(req.password()))
            .role(GlobalRole.ROLE_USER) // Rol por defecto
            .enabled(false) // Usuario no habilitado hasta verificar email
            .verificationToken(verificationToken)
            .build();

        //Salvar
        authUserRepository.save(newUser);

        //Crear el evento
        UserRegisteredEvent event = new UserRegisteredEvent(
            newUserId,
            req.username(),
            req.mail(),
            req.phone(),
            req.companyName(),
            req.taxId(),
            req.timezone(),
            verificationToken
        );

        System.out.println("[EventRegistrationWorkflow] event username: "
                                + event.username() + " " + req.username());

        //publicar en el broker
        rabbitTemplate.convertAndSend("user.exchange", "user.created", event);

        //Respuesta al frontend
        return new RegisterResponse(newUserId, "PENDING_VERIFICATION");

    }

}
