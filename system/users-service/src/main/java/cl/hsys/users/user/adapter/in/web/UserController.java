package cl.hsys.users.user.adapter.in.web;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.hsys.users.user.adapter.in.web.dto.UserResponse;
import cl.hsys.users.user.adapter.in.web.mapper.UserWebMapper;
import cl.hsys.users.user.application.port.in.GetUserCase;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserWebMapper userWebMapper;
    private final GetUserCase getUserCase;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable UUID id) {

        return ResponseEntity.ok(userWebMapper.toResponse(getUserCase.getById(id)));
    }

}
