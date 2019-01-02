package com.connected.accountservice.domain.model.movement;

import com.connected.accountservice.domain.common.enums.MovementType;
import com.connected.accountservice.domain.model.storage.Storage;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "movement")
public class Movement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column
    @Enumerated(EnumType.STRING)
    private MovementType type;

    @Column
    private Double value;

    @JoinColumn
    @ManyToOne
    private Storage storage;

    @Column
    private Boolean repeatMonthly;

    @Column
    private String documentLinkedHash;

    Movement() {

    }

    Movement(final UUID id,
             final MovementType type,
             final Double value,
             final Storage storage,
             final Boolean repeatMonthly,
             final String documentLinkedHash) {
        this.id = id;
        this.type = type;
        this.value = value;
        this.storage = storage;
        this.repeatMonthly = repeatMonthly;
        this.documentLinkedHash = documentLinkedHash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movement movement = (Movement) o;
        return Objects.equals(id, movement.id) &&
                type == movement.type &&
                Objects.equals(value, movement.value) &&
                Objects.equals(storage, movement.storage) &&
                Objects.equals(repeatMonthly, movement.repeatMonthly) &&
                Objects.equals(documentLinkedHash, movement.documentLinkedHash);
    }

    public Double getComputedValue() {
        return type.computeValue(value);
    }

    public boolean isNewMovement() {
        return Objects.isNull(id);
    }

    public boolean hasStorage() {
        return Objects.nonNull(storage);
    }

    public UUID getId() {
        return id;
    }

    public Storage getStorage() {
        return storage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, value, storage, repeatMonthly, documentLinkedHash);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("type", type)
                .append("value", value)
                .append("storage", storage)
                .append("repeatMonthly", repeatMonthly)
                .append("documentLinkedHash", documentLinkedHash)
                .toString();
    }
}
