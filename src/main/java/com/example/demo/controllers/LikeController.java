package com.example.demo.controllers;

import com.example.demo.entity.Like;
import com.example.demo.entity.LikeId;
import com.example.demo.service.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping("/blogs/{blogId}")
    public List<Like> getLikesByBlogId(@PathVariable long blogId) {
        return likeService.getLikesByBlogId(blogId);
    }

    @PostMapping
    public ResponseEntity<?> likeBlog(@RequestBody LikeId likeId) {
        likeService.likeBlog(likeId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> unlikeBlog(@RequestBody LikeId likeId) {
        likeService.dislikeBlog(likeId);
        return ResponseEntity.noContent().build();
    }
}
