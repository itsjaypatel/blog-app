package com.example.demo.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Blog;
import com.example.demo.service.BlogService;

@RestController
@RequestMapping("/blogs")
public class BlogController {
    
    private final BlogService blogService;

    private static final Logger log = LoggerFactory.getLogger(BlogController.class);

    public BlogController(BlogService blogService){
        this.blogService = blogService;
    }

    @PostMapping
    public ResponseEntity<Blog> create(@RequestBody Blog blog){
        log.info("Creating blog: {}", blog);
        Blog createdBlog = blogService.createBlog(blog);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBlog);
    }

    @GetMapping
    public List<Blog> getAll(){
        return blogService.getAllBlogs();
    }

    @GetMapping("/{id}")
    public Blog getById(@PathVariable long id){
        return blogService.getBlogById(id);
    }

    @PutMapping("/{id}")
    public Blog editBlog(@PathVariable long id, @RequestBody Blog blog){
        return blogService.editBlog(id, blog);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable long id){
        blogService.deleteBlog(id);
        return ResponseEntity.noContent().build();
    }
}
