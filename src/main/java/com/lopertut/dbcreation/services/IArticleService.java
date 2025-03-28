package com.lopertut.dbcreation.services;

import com.lopertut.dbcreation.entity.Article;

import java.util.List;
import java.util.Optional;

public interface IArticleService {
    List<Article> getAllArticles();
    Optional<Article> getArticleById(Long id);
    Optional<Article> getArticleByAuthor(Long authorId);
    List<Article> getArticlesByTag(Long tagId);
    Article createArticle(Article article);
    Article updateArticle(Article article);
    void deleteArticle(Long id);
    void addTagToArticle(Long articleId, Long tagId);
}
