package com.lopertut.dbcreation.repositories;

import com.lopertut.dbcreation.entity.ArticleTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleTagRepository extends JpaRepository<ArticleTag, Long> {
    List<ArticleTag> findByTagId(Long tagId);

    boolean existsByArticleIdAndTagId(Long articleId, Long tagId);
}