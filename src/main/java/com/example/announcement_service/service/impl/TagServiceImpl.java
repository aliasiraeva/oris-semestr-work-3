package com.example.announcement_service.service.impl;

import com.example.announcement_service.dto.TagDto;
import com.example.announcement_service.entity.Tag;
import com.example.announcement_service.mapper.TagMapper;
import com.example.announcement_service.repository.TagRepository;
import com.example.announcement_service.service.SubscriptionService;
import com.example.announcement_service.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final SubscriptionService subscriptionService;

    private final TagMapper tagMapper;

    @Override
    public List<TagDto> findAllTags() {
        return tagRepository.findAll().stream()
                .map(tagMapper::toDto)
                .toList();
    }

    @Override
    public TagDto createTag(TagDto tagDto) {
        Tag tag = tagMapper.toEntity(tagDto);
        Tag savedTag = tagRepository.save(tag);
        if (tagRepository.existsById(savedTag.getId())) {
            log.info("{} Тэг успешно добавлен", LocalDateTime.now());
            return tagMapper.toDto(tag);
        } else {
            throw new RuntimeException("Ошибка при сохранении тэга");
        }
    }

    @Override
    public void deleteTag(TagDto tagDto) {
        Tag tag = tagMapper.toEntity(tagDto);
        tagRepository.delete(tag);
        subscriptionService.inactivateSubscriptions(tag);
    }
}
