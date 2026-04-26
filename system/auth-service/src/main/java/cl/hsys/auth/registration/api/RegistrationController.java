package cl.hsys.auth.registration.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.hsys.auth.registration.application.RegistrationWorkflow;
import cl.hsys.auth.registration.dto.RegisterRequest;
import cl.hsys.auth.registration.dto.RegisterResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

//Controller de Registration (solo delega)

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationWorkflow registrationWorkflow;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse>
                            register(@Valid @RequestBody RegisterRequest req) {
        RegisterResponse res = registrationWorkflow.register(req);
        return ResponseEntity.ok(res);
    }

}
