package cl.hsys.auth.auth.adapter.in.web;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.hsys.auth.auth.application.port.in.UserClientAccessQueryCase;
import cl.hsys.auth.auth.domain.UserClientAccess;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/admin/user-access")
@RequiredArgsConstructor
public class UserClientAccessQueryController {

    private final UserClientAccessQueryCase queryCase;

    //Listar todos (Vista principal de admin)
    @GetMapping
    public ResponseEntity<Page<UserClientAccess>> findAll (
            @PageableDefault(size = 10, sort = "clientName") Pageable pageable) {
        return ResponseEntity.ok(queryCase.findAllUserClientAccess(pageable));
    }

    //Listar todos los regsitros de un usuario (para ver el perfil del usuario)
    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<UserClientAccess>> findAllByUserId (
            @PathVariable UUID userId,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(queryCase
                        .findAllUserClientAccessByUserId(userId, pageable));
    }

    //Buscar un acceso especifico (para editar permisos puntuales)
    @GetMapping("/check")
    public ResponseEntity<UserClientAccess> findSpecific(
            @RequestParam UUID userId,
            @RequestParam UUID clientId) {
        return queryCase.findByUserIdAndClientId(userId, clientId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
