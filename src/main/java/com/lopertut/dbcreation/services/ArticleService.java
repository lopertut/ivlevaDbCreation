package com.lopertut.dbcreation.services;

import com.lopertut.dbcreation.entity.Article;
import com.lopertut.dbcreation.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService implements IArticleService {

    @Autowired
    ArticleRepository articleRepository;

    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Optional<Article> getArticleById(Long id) {
        return articleRepository.findById(id);
    }

    @Override
    public Optional<Article> getArticleByAuthor(Long authorId) {
        return articleRepository.findByAuthorId(authorId);
    }

//    @Override
//    public Optional<Article> getArticleByTag(Long tagId) {
//        return articleRepository.findByTagId(tagId);
//    }

    @Override
    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public Article updateArticle(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }
}
