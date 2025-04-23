package com.list.to_do.service;

import java.util.UUID;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.list.to_do.DTO.TaskDTO;
import com.list.to_do.entities.Task;
import com.list.to_do.entities.User;
import com.list.to_do.entities.ENUM.PriorityTask;
import com.list.to_do.exceptions.ResourceNotFound;
import com.list.to_do.repositories.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        return taskRepository.findAllById(user.getId());
    }

    public Task getById(UUID id) {
        return taskRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Task not found " + id));
    }

    public Task createTask(TaskDTO data) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();
        Task newTask = new Task(data.taskName(), data.taskDescription(), data.validityTime(),
                PriorityTask.valueOf(data.priorityTask()));

        user.getTask().add(newTask);
        newTask.setUser(user);

        return taskRepository.save(newTask);
    }

    public void deleteTask(UUID taskId) {
        Optional<Task> taskOpt = taskRepository.findById(taskId);
        Task taskToDelete = taskOpt.orElseThrow(() -> new ResourceNotFound(taskId));

        taskRepository.delete(taskToDelete);
    }
    // criar task

}
