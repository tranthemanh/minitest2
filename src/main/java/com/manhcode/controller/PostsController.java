package com.manhcode.controller;

import com.manhcode.model.Category;
import com.manhcode.model.Posts;
import com.manhcode.repository.dto.ICategoryPostsNumber;
import com.manhcode.service.ICategoryService;
import com.manhcode.service.IPostsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostsController {
    private final IPostsService postsService;
    private final ICategoryService categoryService;

    public PostsController(IPostsService postsService, ICategoryService categoryService) {
        this.postsService = postsService;
        this.categoryService = categoryService;
    }

    @ModelAttribute("categoryList")
    public List<Category> categoryList() {
        return categoryService.findAll();
    }

    @ModelAttribute("categoryPostsNumberList")
    public List<ICategoryPostsNumber> categoryPostsNumberList() {
        return categoryService.getCategoryPostsNumber();
    }

    @GetMapping
    public String index(Model model, @PageableDefault(size = 5) Pageable pageable, @RequestParam(required = false) String query) {
        Page<Posts> postsList = postsService.findByNameContainingIgnoreCase(query, pageable);
        model.addAttribute("postsList", postsList);
        model.addAttribute("query", query);
        return "/index";
    }

    @GetMapping("posts/create")
    public String create(Model model) {
        return "/posts/create";
    }

    @PostMapping("posts/create")
    public String createPost(@ModelAttribute Posts post) {
        postsService.save(post);
        return "redirect:/";
    }


//    @GetMapping("posts/{id}/delete")
//    public String delete(Model model, @PathVariable Long id) {
//        Posts posts = postsService.findById(id);
//        model.addAttribute("posts", posts);
//        return "/posts/delete";
//    }

//    @PostMapping("posts/delete")
//    public String delete(@RequestParam Long id) {
//        Posts posts = postsService.findById(id);
//        postsService.delete(posts);
//        return "redirect:/";
//    }

//    @GetMapping("posts/{id}/update")
//    public String update(Model model, @PathVariable Long id) {
//        Posts posts = postsService.findById(id);
//        model.addAttribute("posts", posts);
//        return "/posts/update";
//    }

    @PostMapping("posts/update")
    public String update(Model model, @ModelAttribute Posts posts) {
        postsService.save(posts);
        return "redirect:/";
    }

    @GetMapping("category/{id}/delete")
    public String deleteCategory(@PathVariable Long id, Model model) {
        Category category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "/category/delete";
    }

    @PostMapping("/category/delete")
    public String deleteCategory(@RequestParam Long id) {
        categoryService.deleteCategoryById(id);
        return "redirect:/";
    }
}
