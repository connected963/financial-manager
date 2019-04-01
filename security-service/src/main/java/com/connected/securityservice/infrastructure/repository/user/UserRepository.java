package com.connected.securityservice.infrastructure.repository.user;

import com.connected.securityservice.domain.model.user.User;
import com.connected.securityservice.domain.querymodel.user.UserDetailQueryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<UserDetailQueryModel> findByEmail(final String email);

}
