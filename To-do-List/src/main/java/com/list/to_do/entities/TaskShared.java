package com.list.to_do.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.list.to_do.entities.ENUM.AccessLevel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.util.UUID;

@Entity
@Table(name = "tb_task_shared", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "task_id", "user_id" })
})
public class TaskShared {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private AccessLevel accessLevel;

    public TaskShared() {
    }

    public TaskShared(User user, Task task, AccessLevel accessLevel) {
        this.user = user;
        this.task = task;
        this.accessLevel = accessLevel;
    }

    public UUID getId() {
        return id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAcessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    public AccessLevel getAcessLevel() {
        return accessLevel;
    }

    @JsonIgnore
    public String toString() {
        return "Acess Level " + getAcessLevel();
    }
}

// tabela intermediaria de mutios pra muitos(user task)
// many-to-one many-to-one muitas TaskShared pra uma task e muitas Taskshare pra
// um user(fluxo)
// ou seja, cada user pode compartilhar varias task e cada task pode ser
// compartilhada por varios users
