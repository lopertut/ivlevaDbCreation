package com.lopertut.dbcreation.controllers;

import com.lopertut.dbcreation.entity.Tag;
import com.lopertut.dbcreation.services.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public String getAllTags(Model model) {
        model.addAttribute("tags", tagService.getAllTags());
        return "tags/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("tag", new Tag());
        return "tags/create";
    }

    @PostMapping("/create")
    public String createTag(@ModelAttribute("tag") Tag tag) {
        tagService.createTag(tag);
        return "redirect:/tags";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
        return "redirect:/tags";
    }
}
