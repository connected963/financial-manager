package com.connected.accountservice.application.inputmodel.storage;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

public class StorageInputModel {

    private UUID id;

    @NotBlank(message = "{storageInputModel.name.blank}")
    @Length(max = 200, message = "{storageInsertInputModel.name.max}")
    private String name;

    @NotBlank(message = "{storageInputModel.documentLinkedHash.blank}")
    @Length(max = 200, message = "{storageInputModel.documentLinkedHash.max}")
    private String documentLinkedHash;

    private StorageInputModel() {

    }

    StorageInputModel(@NotNull(message = "{storageInputModel.id.null}") final UUID id,
                      @NotBlank(message = "{storageInputModel.name.blank}")
                      @Length(max = 200, message = "{storageInsertInputModel.name.max}") final String name,
                      @NotBlank(message = "{storageInputModel.documentLinkedHash.blank}")
                      @Length(max = 200, message = "{storageInputModel.documentLinkedHash.max}") final String documentLinkedHash) {
        this.id = id;
        this.name = name;
        this.documentLinkedHash = documentLinkedHash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StorageInputModel that = (StorageInputModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(documentLinkedHash, that.documentLinkedHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, documentLinkedHash);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDocumentLinkedHash() {
        return documentLinkedHash;
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
