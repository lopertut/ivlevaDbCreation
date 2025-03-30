package com.lopertut.dbcreation.services;

import com.lopertut.dbcreation.entity.Article;
import com.lopertut.dbcreation.entity.ArticleTag;
import com.lopertut.dbcreation.entity.Tag;
import com.lopertut.dbcreation.repositories.ArticleRepository;
import com.lopertut.dbcreation.repositories.ArticleTagRepository;
import com.lopertut.dbcreation.repositories.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ArticleService implements IArticleService {

    private final ArticleRepository articleRepository;
    private final TagRepository tagRepository;
    private final ArticleTagRepository articleTagRepository;

    public ArticleService(ArticleRepository articleRepository, TagRepository tagRepository, ArticleTagRepository articleTagRepository) {
        this.articleRepository = articleRepository;
        this.tagRepository = tagRepository;
        this.articleTagRepository = articleTagRepository;
    }

    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Optional<Article> getArticleById(Long id) {
        return articleRepository.findById(id);
    }

    @Override
    public List<Article> getArticlesByAuthor(Long authorId) {
        return articleRepository.findByAuthorId(authorId);
    }

    @Override
    public List<Article> getArticlesByTag(Long tagId) {
        return articleTagRepository.findByTagId(tagId)
                .stream()
                .map(ArticleTag::getArticle)
                .collect(Collectors.toList());
    }

    @Override
    public ArticleTag addTagToArticle(Long articleId, Long tagId) {
        if (!articleTagRepository.existsByArticleIdAndTagId(articleId, tagId)) {
            Article article = articleRepository.findById(articleId).orElseThrow();
            Tag tag = tagRepository.findById(tagId).orElseThrow();

            ArticleTag articleTag = new ArticleTag();
            articleTag.setArticle(article);
            articleTag.setTag(tag);
            return articleTagRepository.save(articleTag);
        }else {
            throw new RuntimeException("Tag already assigned to this article");
        }
    }

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