package cl.hsys.billing.invoice.adapter.out.persistence.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "invoices",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_invoices_number", columnNames = "invoice_number")
    },
    indexes = {
        @Index(name = "ix_invoices_client", columnList = "client_id"),
        @Index(name = "ix_invoices_status", columnList = "status")
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JpaInvoice {

@Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "invoice_number", nullable = false, length = 50)
    private String invoiceNumber; // Ej: FAC-2026-0001

    @Column(name = "client_id", nullable = false)
    private UUID clientId; // Referencia a JpaClient

    @Column(name = "membership_id", nullable = false)
    private UUID membershipId; // Referencia a JpaMembership

    @Column(name = "total_amount", nullable = false, precision = 19, scale = 4)
    private BigDecimal totalAmount;

    @Column(nullable = false, length = 20)
    @Builder.Default
    private String status = "PENDING"; // PENDING, PAID, CANCELLED

    // Auditoría igual a JpaClient
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Column(name = "created_by", nullable = false, updatable = false)
    private String createdBy;

    @Column(name = "updated_by", nullable = false)
    private String updatedBy;

    // Relación con los items (composición)
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<JpaInvoiceItem> items = new ArrayList<>();

    @PrePersist
    void prePersist() {
        Instant now = Instant.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = Instant.now();
    }

}
