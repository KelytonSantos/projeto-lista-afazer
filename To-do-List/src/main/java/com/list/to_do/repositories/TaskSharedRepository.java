package com.list.to_do.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.list.to_do.entities.TaskShared;

public interface TaskSharedRepository extends JpaRepository<TaskShared, UUID> {
    @Query("SELECT COUNT(taskshared) > 0 FROM TaskShared taskshared WHERE taskshared.task.id = :taskId AND taskshared.user.id = :userId")
    // "SELECT COUNT(taskshared) : contador
    // > 0 : conversor
    // caso eu passe o id da task e user e tiver algo na tabela com essa relação,
    // ele conta 1 retornando true
    boolean existsByTaskAndUser(@Param("taskId") UUID taskId, @Param("userId") UUID userId);

    @Query("SELECT e FROM TaskShared e WHERE e.user.id = :id")
    Optional<TaskShared> findUserById(@Param("id") UUID id);

}
