package com.list.to_do.entities;

import java.io.Serializable;
import java.time.Instant;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.list.to_do.entities.ENUM.PriorityTask;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_task")
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private UUID id;

    @Column(nullable = false, updatable = true)
    private String taskName;

    @Column(columnDefinition = "TEXT", nullable = false, updatable = true)
    private String taskDescription;

    @Column(name = "Validity_Time", nullable = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "America/Sao_Paulo")
    private Instant validityTime;

    @Column(name = "Priority_Task", nullable = false)
    private PriorityTask priorityTask;

    @JsonIgnore
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    public Task(String taskName, String taskDescription, Instant validityTime, PriorityTask priorityTask) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.validityTime = validityTime;
        this.priorityTask = priorityTask;
    }

    public Task() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Instant getValidityTime() {
        return validityTime;
    }

    public void setValidityTime(Instant validityTime) {
        this.validityTime = validityTime;
    }

    public PriorityTask getPriorityTask() {
        return priorityTask;
    }

    public void setPriorityTask(PriorityTask priorityTask) {
        this.priorityTask = priorityTask;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean getExpirate() {
        Instant now = Instant.now();

        if (this.validityTime.isAfter(now))
            return false;
        return true;
    }
}
