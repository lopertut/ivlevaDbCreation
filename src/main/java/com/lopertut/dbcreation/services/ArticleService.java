package com.lopertut.dbcreation.services;

import com.lopertut.dbcreation.entity.Article;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService implements IArticleService {

    @Override
    public List<Article> getAllArticles() {
        return List.of();
    }

    @Override
    public Optional<Article> getArticleById(Long id) {
        return Optional.empty();
    }

    @Override
    public Article createArticle(Article article) {
        return null;
    }

    @Override
    public void updateArticle(Article article) {

    }

    @Override
    public void deleteArticle(Long id) {

    }
}
