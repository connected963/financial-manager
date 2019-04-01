package com.connected.securityservice.domain.model.user;

import com.connected.securityservice.domain.common.enums.DocumentType;
import com.connected.securityservice.domain.common.enums.Role;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private DocumentType documentType;

    @Column
    private String document;

    @CreatedDate
    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDate dateOfBirth;

    @Column
    private Boolean active;

    @Column
    @ElementCollection(targetClass = Role.class)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "id"))
    private Set<Role> roles;

    User() {
    }

    User(final String email, final String password, final String firstName,
         final String lastName, final DocumentType documentType,
         final String document, final LocalDateTime createdAt,
         final LocalDate dateOfBirth, final Boolean active, final Set<Role> roles) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.documentType = documentType;
        this.document = document;
        this.createdAt = createdAt;
        this.dateOfBirth = dateOfBirth;
        this.active = active;
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                documentType == user.documentType &&
                Objects.equals(document, user.document) &&
                Objects.equals(createdAt, user.createdAt) &&
                Objects.equals(dateOfBirth, user.dateOfBirth) &&
                Objects.equals(active, user.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, firstName, lastName, documentType, document, createdAt, dateOfBirth, active);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("email", email)
                .append("password", password)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("documentType", documentType)
                .append("document", document)
                .append("createdAt", createdAt)
                .append("dateOfBirth", dateOfBirth)
                .append("active", active)
                .toString();
    }
}
