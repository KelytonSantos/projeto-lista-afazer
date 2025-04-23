package com.list.to_do.repositories;

import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.list.to_do.entities.Task;

public interface TaskRepository extends JpaRepository<Task, UUID> {
    @Query("SELECT e FROM Task e WHERE e.user.id = :id")
    List<Task> findAllById(@Param("id") UUID id);
}
