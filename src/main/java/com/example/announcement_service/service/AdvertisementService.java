package com.example.announcement_service.service;

import com.example.announcement_service.dto.AdvertisementDto;

import java.util.List;

public interface AdvertisementService {
    List<AdvertisementDto> findAllAds();
    AdvertisementDto updateAd(AdvertisementDto advertisementDto);
    void deleteAd(Long advertisementId);
}
