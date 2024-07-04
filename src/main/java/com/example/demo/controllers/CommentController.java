package com.example.demo.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Comment;
import com.example.demo.service.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {
    
    private final CommentService commentService;

    private static final Logger log = LoggerFactory.getLogger(CommentController.class);

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<Comment> create(@RequestBody Comment comment){
        log.info("Creating comment: {}", comment);
        Comment createdComment = commentService.createComment(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }

    @GetMapping("/{id}")
    public Comment getById(@PathVariable long id){
        return commentService.getCommentById(id);
    }

    @GetMapping("/blogs/{blogId}")
    public List<Comment> getCommentsByBlogId(@PathVariable long blogId){
        return commentService.getCommentsByBlogId(blogId);
    }
    
    @PutMapping("/{id}")
    public Comment editComment(@PathVariable long id, @RequestBody Comment comment){
        return commentService.editComment(id, comment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable long id){
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
