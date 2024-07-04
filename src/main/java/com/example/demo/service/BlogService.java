package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Blog;

public interface BlogService {

    Blog createBlog(Blog blog);

    Blog editBlog(long blogId,Blog blog);

    List<Blog> getAllBlogs();

    Blog getBlogById(long id);

    List<Blog> getBlogsByAuthorId(long authorId);

    void deleteBlog(long id);
}
