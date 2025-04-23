package com.list.to_do.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.list.to_do.DTO.TaskDTO;
import com.list.to_do.DTO.TaskSharedDTO;
import com.list.to_do.entities.Task;
import com.list.to_do.entities.TaskShared;
import com.list.to_do.entities.User;
import com.list.to_do.entities.ENUM.AccessLevel;
import com.list.to_do.entities.ENUM.PriorityTask;
import com.list.to_do.exceptions.ResourceAlreadyExist;
import com.list.to_do.exceptions.ResourceNotAllowed;
import com.list.to_do.exceptions.ResourceNotFound;
import com.list.to_do.repositories.TaskSharedRepository;

@Service
public class TaskSharedService {

    @Autowired
    private TaskSharedRepository taskSharedRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    public List<TaskShared> getAll() {
        return taskSharedRepository.findAll();
    }

    public TaskShared shareTask(TaskSharedDTO sharedTaskDTO) {
        User user = userService.findActiveByEmail(sharedTaskDTO.email());
        Task task = taskService.getById(sharedTaskDTO.id());

        if (taskSharedRepository.existsByTaskAndUser(sharedTaskDTO.id(), user.getId())) {
            throw new ResourceAlreadyExist("Conflict");
        }

        TaskShared taskToShare = new TaskShared();
        taskToShare.setUser(user);
        taskToShare.setTask(task);

        taskToShare.setAcessLevel(AccessLevel.valueOf(sharedTaskDTO.acessLevel()));

        return taskSharedRepository.save(taskToShare);
    }

    public TaskShared updateTask(TaskDTO data) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        TaskShared taskSharedToUpdate = taskSharedRepository.findUserById(user.getId())
                .orElseThrow(() -> new ResourceNotFound(user.getId()));

        if (taskSharedToUpdate.getAcessLevel() == AccessLevel.ADMIN
                || taskSharedToUpdate.getAcessLevel() == AccessLevel.EDIT) {

            taskSharedToUpdate.getTask().setTaskName(data.taskName());
            taskSharedToUpdate.getTask().setTaskDescription(data.taskDescription());
            taskSharedToUpdate.getTask().setPriorityTask(PriorityTask.valueOf(data.priorityTask()));
            taskSharedToUpdate.getTask().setValidityTime(data.validityTime());

            return taskSharedRepository.save(taskSharedToUpdate);
        } else {
            throw new ResourceNotAllowed("Pode não man");
        }
    }

    public void deleteTask(UUID taskId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        TaskShared taskSharedToDelete = taskSharedRepository.findUserById(user.getId())
                .orElseThrow(() -> new ResourceNotFound(user.getId()));

        System.out.println();
        System.out.println(taskSharedToDelete);
        System.out.println();

        if (taskSharedToDelete.getAcessLevel() == AccessLevel.ADMIN) {
            taskSharedRepository.deleteById(taskId);
        } else {
            throw new ResourceNotAllowed("Pode não man");
        }
    }
}

// view n pode deletar
// edit pode editar mas n apagar
// admin pode editar e apagar