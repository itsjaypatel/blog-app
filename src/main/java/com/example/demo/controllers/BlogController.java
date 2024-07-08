package com.example.demo.controllers;

import java.util.List;

import com.example.demo.assembler.BlogModelAssembler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/blogs")
public class BlogController {
    
    private final BlogService blogService;

    private final BlogModelAssembler assembler;

    private static final Logger log = LoggerFactory.getLogger(BlogController.class);

    public BlogController(BlogService blogService, BlogModelAssembler assembler){
        this.blogService = blogService;
        this.assembler = assembler;
    }

    @PostMapping
    public ResponseEntity<EntityModel<Blog>> create(@RequestBody Blog blog){
        log.info("Creating blog: {}", blog);
        Blog createdBlog = blogService.createBlog(blog);
        return ResponseEntity.created(linkTo(methodOn(BlogController.class).getById(createdBlog.getId())).toUri()).body(assembler.toModel(createdBlog));
    }

    @GetMapping
    public CollectionModel<EntityModel<Blog>> getAll(){
        List<EntityModel<Blog>> blogs = blogService.getAllBlogs().stream().map(assembler::toModel).toList();
        return CollectionModel.of(blogs, linkTo(methodOn(BlogController.class).getAll()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Blog> getById(@PathVariable long id){
        return assembler.toModel(blogService.getBlogById(id));
    }

    @GetMapping("/users/{userId}")
    public CollectionModel<EntityModel<Blog>> getBlogsByAuthorId(@PathVariable long userId){
        List<EntityModel<Blog>> blogs = blogService.getBlogsByAuthorId(userId).stream().map(assembler::toModel).toList();
        return CollectionModel.of(blogs, linkTo(methodOn(BlogController.class).getAll()).withSelfRel());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Blog>> editBlog(@PathVariable long id, @RequestBody Blog blog){
        Blog updatedBlog = blogService.editBlog(id, blog);
        return ResponseEntity.created(linkTo(methodOn(BlogController.class).getById(updatedBlog.getId())).toUri()).body(assembler.toModel(updatedBlog));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable long id){
        blogService.deleteBlog(id);
        return ResponseEntity.noContent().build();
    }
}
