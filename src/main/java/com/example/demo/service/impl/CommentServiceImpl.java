package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Comment;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CommentRepository;
import com.example.demo.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment editComment(long commentId, Comment comment) {
        return commentRepository
        .findById(commentId)
        .map(currentComment -> {
            currentComment.setDescription(comment.getDescription());
            return commentRepository.save(currentComment);
        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id: " + commentId));
    }

    @Override
    public Comment getCommentById(long id) {
        return commentRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Comment not found with id: " + id));
    }

    @Override
    public List<Comment> getCommentsByBlogId(long blogId) {
        return commentRepository.findByBlogId(blogId);
    }

    @Override
    public void deleteComment(long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if(comment.isEmpty()){
            throw new ResourceNotFoundException("Comment not found with id: " + id);
        }
        commentRepository.delete(comment.get());
    }
    
}
