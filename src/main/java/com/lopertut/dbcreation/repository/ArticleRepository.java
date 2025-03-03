package com.lopertut.dbcreation.repository;

import com.lopertut.dbcreation.entity.Article;
import com.lopertut.dbcreation.entity.Tag;
import com.lopertut.dbcreation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    Optional<Article> findByTitle(String title);
    Optional<Article> findByTag(List<Tag> tags);
    Optional<Article> findByAuthor(User author);
}
