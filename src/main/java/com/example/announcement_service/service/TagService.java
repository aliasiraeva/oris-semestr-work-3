package com.example.announcement_service.service;

import com.example.announcement_service.dto.TagDto;

import java.util.List;

public interface TagService {
    List<TagDto> findAllTags();
    TagDto createTag(TagDto tagDto);
    void deleteTag(TagDto tagDto);
}
