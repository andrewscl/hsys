package cl.hsys.auth.registration.application;

import cl.hsys.auth.registration.dto.RegisterRequest;
import cl.hsys.auth.registration.dto.RegisterResponse;

//Interfaz del Workflow (la “pieza intercambiable”)

public interface RegistrationWorkflow {
    RegisterResponse register(RegisterRequest req);
}
