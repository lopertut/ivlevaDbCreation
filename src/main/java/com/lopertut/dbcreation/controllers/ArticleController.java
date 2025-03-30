package com.lopertut.dbcreation.controllers;

import com.lopertut.dbcreation.entity.Article;
import com.lopertut.dbcreation.entity.ArticleTag;
import com.lopertut.dbcreation.repositories.ArticleTagRepository;
import com.lopertut.dbcreation.services.ArticleService;
import com.lopertut.dbcreation.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final TagService tagService;

    public ArticleController(ArticleService articleService, TagService tagService) {
        this.articleService = articleService;
        this.tagService = tagService;
    }

    @GetMapping
    public String getAllArticles(Model model) {
        model.addAttribute("articles", articleService.getAllArticles());
        return "articles/list";
    }

    @GetMapping("/{id}")
    public String getArticleById(@PathVariable Long id, Model model) {
        Optional<Article> articleById = articleService.getArticleById(id);
        model.addAttribute("article", articleById.get());
        return "articles/details";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("article", new Article());
        model.addAttribute("allTags", tagService.getAllTags());
        model.addAttribute("articleTag", new ArticleTag());
        return "articles/create";
    }

    @PostMapping("/create")
    public String createArticle(@ModelAttribute("article") Article article, @RequestParam("tags") List<Long> tags) {
        Article newArticle = articleService.createArticle(article);
        System.out.println(newArticle.getId());

        if (tags != null) {
            for (Long tagId : tags) {
                System.out.println(tagId);
                articleService.addTagToArticle(newArticle.getId(), tagId);
            }
        }
        return "redirect:/articles";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return "redirect:/articles";
    }

    @GetMapping("/{id}/update")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Optional<Article> optionalArticle = articleService.getArticleById(id);
        model.addAttribute("article", optionalArticle.get());
        return "articles/update";
    }

    @PutMapping("/{id}/update")
    public String updateArticle(@ModelAttribute("article") Article article) {
        articleService. updateArticle(article);
        return "redirect:/articles";
    }

    @GetMapping("/by-author/{authorId}")
    public String getArticlesByAuthor(@PathVariable Long authorId, Model model) {
        model.addAttribute("articles", articleService.getArticlesByAuthor(authorId));
        return "articles/list";
    }

    @GetMapping("/by-tag/{tagId}")
    public String getArticlesByTag(@PathVariable Long tagId, Model model) {
        model.addAttribute("articles", articleService.getArticlesByTag(tagId));
        return "articles/list";
    }

//    @GetMapping("/search")
//    public String search() {
//
//    }
}
