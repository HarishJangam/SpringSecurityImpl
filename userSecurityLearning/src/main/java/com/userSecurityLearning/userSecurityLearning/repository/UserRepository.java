package com.userSecurityLearning.userSecurityLearning.repository;

import com.userSecurityLearning.userSecurityLearning.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsernameAndIsActive(String username, boolean isActive);
}
