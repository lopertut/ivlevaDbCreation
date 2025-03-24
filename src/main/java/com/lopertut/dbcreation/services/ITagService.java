package com.lopertut.dbcreation.services;

import com.lopertut.dbcreation.entity.Tag;

import java.util.List;
import java.util.Optional;

public interface ITagService {
    List<Tag> getAllTags();
    Tag createTag(Tag tag);
    void deleteTag(Long id);
}
