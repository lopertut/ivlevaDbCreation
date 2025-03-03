package com.lopertut.dbcreation.config;

import com.github.javafaker.Faker;
import com.lopertut.dbcreation.entity.*;
import com.lopertut.dbcreation.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;
import java.util.*;

@Configuration
public class JavaFaker {

    @Bean
    public CommandLineRunner loadData(
            UserRepository userRepository,
            ArticleRepository articleRepository,
            ArticleCommentRepository articleCommentRepository,
            ArticleFavoriteRepository articleFavoriteRepository,
            ArticleTagRepository articleTagRepository,
            TagRepository tagRepository) {
        return args -> {
            Faker faker = new Faker();
            List<User> users = new ArrayList<>();
            List<Article> articles = new ArrayList<>();
            List<ArticleComment> articleComments = new ArrayList<>();
            List<ArticleFavorite> articleFavorites = new ArrayList<>();
            List<ArticleTag> articleTags = new ArrayList<>();
            List<Tag> tags = new ArrayList<>();

            // Генерация фейковых пользователей
            for (int i = 0; i < 10; i++) {
                User user = new User();
                setField(user, "email", faker.internet().emailAddress());
                setField(user, "username", faker.name().username());
                setField(user, "image_url", faker.internet().avatar());
                setField(user, "password", faker.internet().password());
                setField(user, "bio", faker.lorem().sentence());
                users.add(user);
            }
            userRepository.saveAll(users);

            // Генерация фейковых тегов
            for (int i = 0; i < 5; i++) {
                Tag tag = new Tag();
                setField(tag, "name", faker.lorem().word());
                tags.add(tag);
            }
            tagRepository.saveAll(tags);

            // Генерация фейковых статей
            for (int i = 0; i < 20; i++) {
                Article article = new Article();
                setField(article, "description", faker.lorem().sentence());
                setField(article, "slug", faker.internet().slug());
                setField(article, "title", faker.lorem().sentence());
                setField(article, "content", faker.lorem().paragraph(5));
                setField(article, "author", users.get(faker.number().numberBetween(0, users.size()))); // Случайный автор
                articles.add(article);
            }
            articleRepository.saveAll(articles);

            // Генерация фейковых комментариев
            for (int i = 0; i < 50; i++) {
                ArticleComment comment = new ArticleComment();
                setField(comment, "content", faker.lorem().paragraph());
                setField(comment, "content", articles.get(faker.number().numberBetween(0, users.size()))); // Случайная статья
                setField(comment, "author", users.get(faker.number().numberBetween(0, users.size()))); // Случайный автор
                articleComments.add(comment);
            }
            articleCommentRepository.saveAll(articleComments);

            // Генерация фейковых избранных статей
            for (int i = 0; i < 10; i++) {
                ArticleFavorite favorite = new ArticleFavorite();
                favorite.setArticles(new ArrayList<>(articles.subList(0, faker.random().nextInt(1, 5)))); // Случайные статьи
                favorite.setUsers(new ArrayList<>(users.subList(0, faker.random().nextInt(1, 5)))); // Случайные пользователи
                articleFavorites.add(favorite);
            }
            articleFavoriteRepository.saveAll(articleFavorites);

            // Генерация фейковых тегов для статей
            for (int i = 0; i < 10; i++) {
                ArticleTag articleTag = new ArticleTag();
                articleTag.setArticles(new HashSet<>(articles.subList(0, faker.random().nextInt(1, 5)))); // Случайные статьи
                articleTag.setTags(new HashSet<>(tags.subList(0, faker.random().nextInt(1, 3)))); // Случайные теги
                articleTags.add(articleTag);
            }
            articleTagRepository.saveAll(articleTags);
        };
    }

    private void setField(Object object, String fieldName, Object value) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}