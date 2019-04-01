package com.connected.securityservice.domain.querymodel.user;

import com.connected.securityservice.domain.common.enums.DocumentType;
import com.connected.securityservice.domain.common.enums.Role;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserDetailQueryModel implements UserDetails {

    private static final long serialVersionUID = 1L;
    private static final String DOCUMENT_LINKED_HASH_PATTERN = "%s%s";

    private UUID id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private DocumentType documentType;

    private String document;

    private Boolean active;

    private Set<Role> roles;

    public UserDetailQueryModel(final UUID id, final String email, final String password,
                                final String firstName, final String lastName,
                                final DocumentType documentType, final String document,
                                final Boolean active, final Set<Role> roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.documentType = documentType;
        this.document = document;
        this.active = active;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(Role::name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getDocumentHash() {
        return String.format(DOCUMENT_LINKED_HASH_PATTERN,
                documentType.name(), document);
    }
}
