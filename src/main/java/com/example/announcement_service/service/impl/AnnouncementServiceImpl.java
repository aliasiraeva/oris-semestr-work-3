package com.example.announcement_service.service.impl;

import com.example.announcement_service.dictionary.AnnouncementCategory;
import com.example.announcement_service.dto.AnnouncementDto;
import com.example.announcement_service.entity.Announcement;
import com.example.announcement_service.exception.AnnouncementNotFoundException;
import com.example.announcement_service.geolocation.client.GeoClient;
import com.example.announcement_service.geolocation.model.GeoLocation;
import com.example.announcement_service.geolocation.util.GeoUtils;
import com.example.announcement_service.mapper.AnnouncementMapper;
import com.example.announcement_service.repository.AnnouncementRepository;
import com.example.announcement_service.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnnouncementServiceImpl implements AnnouncementService {
    private final AnnouncementRepository announcementRepository;
    private final AnnouncementMapper announcementMapper;

    private final GeoClient geoClient;

    @Override
    public List<AnnouncementDto> getAnnouncementsByDate() {
        return announcementRepository.findAll().stream()
                .sorted(Comparator.comparing(Announcement::getDate))
                .map(announcementMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AnnouncementDto getAnnouncementById(Long id) {
        if (announcementRepository.existsById(id)) {
            return announcementRepository.findById(id).map(announcementMapper::toDto).get();
        } else {
            throw new AnnouncementNotFoundException(id);
        }
    }

    @Override
    public AnnouncementDto saveAnnouncement(AnnouncementDto announcementDto) {
        if ((announcementDto.getLat() == null || announcementDto.getLng() == null) && !(announcementDto.getAddress() == null)) {
            GeoLocation location = geoClient.getLocation(announcementDto.getAddress());
            announcementDto.setLng(location.getLng());
            announcementDto.setLat(location.getLat());
        }
        if (announcementDto.getAddress() == null && !(announcementDto.getLat() == null || announcementDto.getLng() == null)) {
            announcementDto.setAddress(geoClient.getAddress(GeoLocation.builder().lng(announcementDto.getLng()).lat(announcementDto.getLat()).build()));
        }
        if ((announcementDto.getAddress() == null) && (announcementDto.getLat() == null || announcementDto.getLng() == null)) {
            throw new IllegalArgumentException();
        }
        announcementDto.setDate(LocalDateTime.now());
        log.info("категория: {}", announcementDto.getCategory());

        Announcement announcement = announcementMapper.toEntity(announcementDto);
        announcementRepository.save(announcement);
        return announcementDto;
    }

    @Override
    public void deleteAnnouncement(Long id) {
        announcementRepository.deleteById(id);
    }

    @Override
    public AnnouncementDto updateAnnouncement(Long id, AnnouncementDto announcementDto) {
        if(!announcementRepository.existsById(id)) {
            throw new AnnouncementNotFoundException(id);
        }
        announcementRepository.deleteById(id);
        saveAnnouncement(announcementDto);
        return announcementDto;
    }

    @Override
    public List<AnnouncementDto> getNearestAnnouncements(double lat, double lng, int distance) {
        GeoLocation location = GeoLocation.builder().lat(lat).lng(lng).build();
        return announcementRepository.findAll().stream()
                .filter(announcement -> calcDistance(announcement, location) <= distance)
                .sorted(Comparator.comparing(announcement -> calcDistance(announcement, location)))
                .map(announcementMapper::toDto)
                .toList();
    }

    @Override
    public List<AnnouncementDto> getAnnouncementsByCategories(List<AnnouncementCategory> categories) {
        return announcementRepository.findAll().stream()
                .filter(announcement -> categories.contains(announcement.getCategory()))
                .map(announcementMapper::toDto)
                .toList();
    }

    private int calcDistance(Announcement announcement, GeoLocation location) {
        GeoLocation announcementLocation = GeoLocation.builder()
                .lat(announcement.getLat())
                .lng(announcement.getLng())
                .build();
        return (int)(GeoUtils.calculateDistance(location, announcementLocation));
    }
}
