package com.connected.accountservice.domain.model.storage;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "storage")
public class Storage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column
    private String name;

    @Column
    private String documentLinkedHash;

    Storage() {

    }

    Storage(final UUID id,
            final String name,
            final String documentLinkedHash) {
        this.id = id;
        this.name = name;
        this.documentLinkedHash = documentLinkedHash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Storage storage = (Storage) o;
        return Objects.equals(id, storage.id) &&
                Objects.equals(name, storage.name) &&
                Objects.equals(documentLinkedHash, storage.documentLinkedHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, documentLinkedHash);
    }

    public Storage withId(final UUID newId) {
        return new StorageBuilder()
                .withId(newId)
                .withName(this.name)
                .withDocumentLinkedHash(this.documentLinkedHash)
                .build();
    }

    public Storage withName(final String newName) {
        return new StorageBuilder()
                .withId(this.id)
                .withName(newName)
                .withDocumentLinkedHash(this.documentLinkedHash)
                .build();
    }

    public Storage withDocumentLinkedHash(final String newDocumentLinkedHash) {
        return new StorageBuilder()
                .withId(this.id)
                .withName(this.name)
                .withDocumentLinkedHash(newDocumentLinkedHash)
                .build();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("documentLinkedHash", documentLinkedHash)
                .toString();
    }
}
