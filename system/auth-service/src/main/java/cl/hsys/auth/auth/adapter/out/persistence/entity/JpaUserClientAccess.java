package cl.hsys.auth.auth.adapter.out.persistence.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.*;

@Entity
@Table(
    name = "user_client_access",
    uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "client_id"})
)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class JpaUserClientAccess {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "client_id", nullable = false)
    private UUID clientId;

    // 💡 IMPORTANTE: ¿Qué puede hacer el usuario en esta empresa?
    @Column(name = "client_role", nullable = false)
    private String clientRole; // Ejemplo: "ADMIN", "OWNER"

    // 💡 OPCIONAL: Nombre de la empresa para mostrarlo en el frontend sin consultar otro micro
    @Column(name = "client_name")
    private String clientName;

}
