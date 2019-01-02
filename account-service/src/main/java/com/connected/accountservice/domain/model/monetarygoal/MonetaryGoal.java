package com.connected.accountservice.domain.model.monetarygoal;

import com.connected.accountservice.domain.model.storage.Storage;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "monetary_goal")
public class MonetaryGoal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column
    private Double value;

    @ManyToOne
    @JoinColumn
    private Storage storage;

    MonetaryGoal() {

    }

    MonetaryGoal(final UUID id, final Double value, final Storage storage) {
        this.id = id;
        this.value = value;
        this.storage = storage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonetaryGoal that = (MonetaryGoal) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(value, that.value) &&
                Objects.equals(storage, that.storage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, storage);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("value", value)
                .append("storage", storage)
                .toString();
    }
}
