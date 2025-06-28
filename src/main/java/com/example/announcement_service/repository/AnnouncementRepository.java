package com.example.announcement_service.repository;

import com.example.announcement_service.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    List<Announcement> findAllByUserId(Long userId);
}