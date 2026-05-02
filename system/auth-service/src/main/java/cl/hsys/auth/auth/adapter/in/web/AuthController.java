package cl.hsys.auth.auth.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.hsys.auth.auth.application.AuthService;
import cl.hsys.auth.auth.dto.LoginRequest;
import cl.hsys.auth.auth.dto.LoginResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req){
        LoginResponse response = authService.authenticate(req);
        return ResponseEntity.ok(response);
    }



}
