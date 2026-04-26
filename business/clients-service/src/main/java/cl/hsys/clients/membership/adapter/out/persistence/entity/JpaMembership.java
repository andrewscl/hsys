package cl.hsys.clients.membership.adapter.out.persistence.entity;

import java.time.Instant;
import java.util.UUID;

import cl.hsys.clients.client.adapter.out.persistence.entity.JpaClient;
import cl.hsys.clients.membership.domain.enums.BusinessRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
  name = "memberships",
  uniqueConstraints = @UniqueConstraint(
      name = "uk_membership_user_client",
      columnNames = {"user_id", "client_id"}
    ),
  indexes = {
    @Index(name = "ix_memberships_user", columnList = "user_id"),
    @Index(name = "ix_memberships_client", columnList = "client_id"),
    @Index(name = "ix_memberships_role", columnList = "role"),
    @Index(name = "ix_memberships_active", columnList = "active")
  }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JpaMembership {

    @Id
    @Column(name="id", columnDefinition="uuid", updatable=false, nullable=false)
    private UUID id;

    //ID del users-service (si esta en otro microservicio se guarda como valor)
    @Column(name= "user_id", nullable = false, columnDefinition = "uuid")
    private UUID userId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", nullable = false,
      foreignKey = @ForeignKey(name = "fk_membership_client"))
    private JpaClient client;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(name = "role", nullable = false, length = 20)
    private BusinessRole role = BusinessRole.OWNER;

    @Column(nullable = false)
    @Builder.Default
    private Boolean active = true;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Column(name = "created_by", nullable = false, updatable = false)
    private String createdBy;

    @Column(name = "updated_by", nullable = false)
    private String updatedBy;

    @PrePersist
    void prePersist(){
        Instant now = Instant.now();
        this.updatedAt = now;
        this.createdAt = now;
    }

    @PreUpdate
    void preUpdate() {
      this.updatedAt = Instant.now();
    }

}
