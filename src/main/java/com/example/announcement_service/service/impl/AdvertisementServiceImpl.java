package com.example.announcement_service.service.impl;

import com.example.announcement_service.dto.AdvertisementDto;
import com.example.announcement_service.exception.AdvertisementNotFoundException;
import com.example.announcement_service.mapper.AdvertisementMapper;
import com.example.announcement_service.repository.AdvertisementRepository;
import com.example.announcement_service.service.AdvertisementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementRepository advertisementRepository;

    private final AdvertisementMapper advertisementMapper;

    @Override
    public List<AdvertisementDto> findAllAds() {
        return advertisementRepository.findAll().stream().map(advertisementMapper::toDto).toList();
    }

    @Override
    public AdvertisementDto updateAd(AdvertisementDto advertisementDto) {
        return null;
    }

    @Override
    public void deleteAd(Long advertisementId) {
        if (advertisementRepository.existsById(advertisementId)) {
            advertisementRepository.deleteById(advertisementId);
        } else {
            throw new AdvertisementNotFoundException(advertisementId);
        }
    }
}
