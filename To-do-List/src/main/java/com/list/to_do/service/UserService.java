package com.list.to_do.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.list.to_do.entities.User;
import com.list.to_do.exceptions.ResourceNotFound;
import com.list.to_do.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findActiveById(UUID id) {
        return userRepository.findActiveById(id).orElseThrow(() -> new ResourceNotFound(id));
    }

    public User findActiveByEmail(String email) {
        return userRepository.findActiveByEmail(email)
                .orElseThrow(() -> new ResourceNotFound("Email not found " + email));
    }

    public User deleteById(UUID id) {
        User userToDelete = userRepository.findActiveById(id).orElseThrow(() -> new ResourceNotFound(id));

        userToDelete.setDeletedAt(Instant.now());

        return userRepository.save(userToDelete);
    }
}
