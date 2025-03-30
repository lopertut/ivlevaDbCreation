package com.lopertut.dbcreation.services;

import com.lopertut.dbcreation.entity.Tag;

import java.util.List;

public interface ITagService {
    List<Tag> getAllTags();
    Tag createTag(Tag tag);
    void deleteTag(Long id);
}
