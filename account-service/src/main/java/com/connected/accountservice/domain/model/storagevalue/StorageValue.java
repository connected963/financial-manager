package com.connected.accountservice.domain.model.storagevalue;

import com.connected.accountservice.domain.model.movement.Movement;
import com.connected.accountservice.domain.model.storage.Storage;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "storage_value")
public class StorageValue implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column
    private Double value;

    @Column
    @CreationTimestamp
    private LocalDateTime date;

    @JoinColumn
    @ManyToOne
    private Storage storage;

    StorageValue() {

    }

    StorageValue(final UUID id, final Double value, final LocalDateTime date,
                 final Storage storage) {
        this.id = id;
        this.storage = storage;
        this.date = date;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StorageValue that = (StorageValue) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(value, that.value) &&
                Objects.equals(date, that.date) &&
                Objects.equals(storage, that.storage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, date, storage);
    }

    public StorageValue withId(final UUID newId) {
        return new StorageValueBuilder()
                .withId(newId)
                .withValue(this.value)
                .withStorage(this.storage)
                .build();
    }

    public StorageValue withValue(final Double newValue) {
        return new StorageValueBuilder()
                .withId(this.id)
                .withValue(newValue)
                .withStorage(this.storage)
                .build();
    }

    public StorageValue withStorage(final Storage newStorage) {
        return new StorageValueBuilder()
                .withId(this.id)
                .withValue(this.value)
                .withStorage(newStorage)
                .build();
    }

    public StorageValue recalculateAddingMovement(final Movement movement) {
        final var movementComputedValue = movement.getComputedValue();
        final var newValue = value + movementComputedValue;

        return withValue(newValue);
    }

    public StorageValue recalculateRemovingMovement(final Movement movement) {
        final var movementComputedValue = movement.getComputedValue();
        final var newValue = value - movementComputedValue;

        return withValue(newValue);
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "StorageValue{" +
                "id=" + id +
                ", value=" + value +
                ", date=" + date +
                ", storage=" + storage +
                '}';
    }
}
