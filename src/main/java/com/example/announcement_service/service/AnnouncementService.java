package com.example.announcement_service.service;

import com.example.announcement_service.dictionary.AnnouncementCategory;
import com.example.announcement_service.dto.AnnouncementDto;

import java.util.List;

public interface AnnouncementService {
    List<AnnouncementDto> getAnnouncementsByDate();
    AnnouncementDto getAnnouncementById(Long id);
    AnnouncementDto saveAnnouncement(AnnouncementDto announcementDto);
    void deleteAnnouncement(Long id);
    AnnouncementDto updateAnnouncement(Long id, AnnouncementDto announcementDto);
    //List<ShortAnnouncement> getShortAnnouncements();
    List<AnnouncementDto> getNearestAnnouncements(double lat, double lng, int distance);
    List<AnnouncementDto> getAnnouncementsByCategories(List<AnnouncementCategory> categories);
}
