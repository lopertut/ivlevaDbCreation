package com.lopertut.dbcreation.services;

import com.lopertut.dbcreation.entity.Article;

import java.util.List;
import java.util.Optional;

public interface IArticleService {
    List<Article> getAllArticles();
    Optional<Article> getArticleById(Long id);
    Article createArticle(Article article);
    void updateArticle(Article article);
    void deleteArticle(Long id);
}
