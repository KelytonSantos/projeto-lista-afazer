package com.list.to_do.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import com.list.to_do.entities.User;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("SELECT e FROM User e WHERE e.deletedAt IS NULL")
    Optional<List<User>> findAllActive();

    @Query("SELECT e FROM User e WHERE e.id = :id AND e.deletedAt IS NULL")
    Optional<User> findActiveById(@Param("id") UUID id);

    @Query("SELECT e FROM User e WHERE e.login = :login AND e.deletedAt IS NULL")
    Optional<User> findActiveByEmail(@Param("login") String login);

    UserDetails findByLogin(String login);
}
