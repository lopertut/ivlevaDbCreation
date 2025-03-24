package com.lopertut.dbcreation.controllers;

import com.lopertut.dbcreation.entity.Article;
import com.lopertut.dbcreation.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

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
        return "articles/create";
    }

    @PostMapping("/create")
    public String createArticle(@ModelAttribute("article") Article article) {
        articleService.createArticle(article);
        return "redirect:/articles";
    }

    @GetMapping("/{id}/delete")
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
}
