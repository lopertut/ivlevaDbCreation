package com.lopertut.dbcreation.repositories;

import com.lopertut.dbcreation.entity.Article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByTitle(String title);
    List<Article> findByAuthorId(Long authorId);

    @Query("SELECT a FROM Article a WHERE a.uploaded_at BETWEEN :startDate AND :endDate")
    Optional<Article> findByUploadedDateBetween(Date startDate, Date endDate);
}