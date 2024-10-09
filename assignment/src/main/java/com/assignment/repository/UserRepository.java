package com.assignment.repository;

import com.assignment.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface User repository.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    /**
     * Find by email optional.
     *
     * @param email the email
     * @return the optional
     */
    Optional<UserEntity> findByEmail(String email);
}
