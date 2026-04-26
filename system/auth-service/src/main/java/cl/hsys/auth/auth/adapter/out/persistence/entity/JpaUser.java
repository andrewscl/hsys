package cl.hsys.auth.auth.adapter.out.persistence.entity;

import java.util.UUID;

import cl.hsys.auth.auth.domain.enums.GlobalRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "auth_users") // Nombre diferente para no confundir
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class JpaUser {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String mail;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)    
    private GlobalRole role;

    private boolean enabled;

    private String verificationToken;

}
