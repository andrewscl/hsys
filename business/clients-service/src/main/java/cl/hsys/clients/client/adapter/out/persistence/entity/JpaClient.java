package cl.hsys.clients.client.adapter.out.persistence.entity;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import cl.hsys.clients.membership.adapter.out.persistence.entity.JpaMembership;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
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
    name = "clients",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_clients_tax_id", columnNames = "tax_id")
    },
    indexes = {
        @Index(name = "ix_clients_active", columnList = "active")
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JpaClient {

    @Id
    @Column(name="id", columnDefinition="uuid", updatable=false, nullable=false)
    private UUID id;

    @Column(name = "owner_id", nullable = false, columnDefinition = "uuid")
    private UUID ownerId;

    // Nombre comercial
    @Column(nullable = false, length = 160)
    private String name;

    // Razón social
    @Column(name = "legal_name", length = 200)
    private String legalName;

    // RUT
    @Column(name = "tax_id", length = 32)
    private String taxId;

    @Column(name = "contact_email", length = 100)
    private String contactEmail;

    @Column(name= "contact_phone", length = 30)
    private String contactPhone;

    @Column(name = "timezone", length = 50)
    private String timezone;  // "America/Santiago"

    @Column(nullable = false)
    @Builder.Default
    private Boolean active = true;

    // Auditoría básica
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
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = Instant.now();
    }

    // Relación con membresías
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<JpaMembership> memberships = new HashSet<>();

}