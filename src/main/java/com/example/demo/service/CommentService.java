package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Comment;

public interface CommentService {

    Comment createComment(Comment comment);

    Comment editComment(long commentId,Comment comment);

    Comment getCommentById(long id);

    List<Comment> getCommentsByBlogId(long                    blogId);

    void deleteComment(long id);
}
