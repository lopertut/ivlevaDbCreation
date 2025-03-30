package com.lopertut.dbcreation.services;

import com.lopertut.dbcreation.entity.Article;
import com.lopertut.dbcreation.entity.ArticleTag;

import java.util.List;
import java.util.Optional;

public interface IArticleService {
    List<Article> getAllArticles();
    Optional<Article> getArticleById(Long id);
    List<Article> getArticlesByAuthor(Long authorId);
    List<Article> getArticlesByTag(Long tagId);
    Article createArticle(Article article);
    Article updateArticle(Article article);
    void deleteArticle(Long id);
    ArticleTag addTagToArticle(Long articleId, Long tagId);
    List<Article> getArticleByTitle(String title);
}
