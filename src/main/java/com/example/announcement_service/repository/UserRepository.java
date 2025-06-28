package com.example.announcement_service.repository;

import com.example.announcement_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByPhoneNumber(String phoneNumber);
    Optional<User> findUserByPhoneNumber(String phoneNumber);
}
