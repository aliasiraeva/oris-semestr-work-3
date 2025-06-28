package com.example.announcement_service.controller;

import com.example.announcement_service.dto.AnnouncementDto;
import com.example.announcement_service.service.AnnouncementService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/announcement")
@RequiredArgsConstructor
public class AnnouncementController {
    private final AnnouncementService announcementService;

    @GetMapping("/list")
    public List<AnnouncementDto> getAllAnnouncements() {
        return announcementService.getAnnouncementsByDate();
    }

    @GetMapping("/{id}")
    public AnnouncementDto getAnnouncementById(@PathVariable Long id) {
        return announcementService.getAnnouncementById(id);
    }

    @PostMapping
    public AnnouncementDto postAnnouncement(@Valid @RequestBody AnnouncementDto announcementDto) {
        return announcementService.saveAnnouncement(announcementDto);
    }

    @DeleteMapping("/{id}")
    public void deleteAnnouncement(@PathVariable Long id) {
        announcementService.deleteAnnouncement(id);
    }

    @PutMapping("/{id}")
    public void putAnnouncement(@PathVariable Long id, @Valid @RequestBody AnnouncementDto announcementDto) {
        announcementService.updateAnnouncement(id, announcementDto);
    }


    @GetMapping("/nearest")
    public List<AnnouncementDto> getNearestAnnouncements(@NotNull double lat, @NotNull double lng, @Size(min=10, max=10000) int distance) {
        return announcementService.getNearestAnnouncements(lat, lng, distance);
    }

    @GetMapping("/")
    public String getHomepage(Model model) {
        List<AnnouncementDto> announcements = announcementService.getAnnouncementsByDate();
        model.addAttribute("announcements", announcements);
        return "index.jsp";
    }
}
