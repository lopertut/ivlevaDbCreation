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
    public String getAllUsers(Model model) {
        model.addAttribute("articles", articleService.getAllArticles());
        return "articles/list";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable Long id, Model model) {
        Optional<Article> articleById = articleService.getArticleById(id);
        model.addAttribute("article", articleById.get());
        return "users/details";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("article", new Article());
        return "articles/create";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("article") Article article) {
        articleService.createArticle(article);
        return "redirect:/articles";
    }

    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
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
    public String updateUser(@ModelAttribute("article") Article article) {
        articleService.updateArticle(article);
        return "redirect:/articles";
    }
}
