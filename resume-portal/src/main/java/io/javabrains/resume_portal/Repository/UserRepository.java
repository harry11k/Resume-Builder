package io.javabrains.resume_portal.Repository;

import io.javabrains.resume_portal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByuserName(String UserName);
}
