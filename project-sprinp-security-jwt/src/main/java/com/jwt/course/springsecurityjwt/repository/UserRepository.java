package com.jwt.course.springsecurityjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.jwt.course.springsecurityjwt.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByLogin(String email);

}
