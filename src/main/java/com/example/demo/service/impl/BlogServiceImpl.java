package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Blog;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BlogRepository;
import com.example.demo.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService{

    private final BlogRepository blogRepository;

    public BlogServiceImpl(BlogRepository blogRepository){
        this.blogRepository = blogRepository;
    }

    @Override
    public Blog createBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    @Override
    public Blog getBlogById(long id) {
        return blogRepository
        .findById(id)
        .orElseThrow(
            ()-> new ResourceNotFoundException("Blog not found with id: " + id)
        );
    }

    @Override
    public List<Blog> getBlogsByAuthorId(long authorId) {
        return blogRepository.findByAuthorId(authorId);
    }

    @Override
    public Blog editBlog(long blogId, Blog blog) {
        return blogRepository
        .findById(blogId)
        .map(currentBlog -> {
            currentBlog.setTitle(blog.getTitle());
            currentBlog.setContent(blog.getContent());
            return blogRepository.save(currentBlog);
        })
        .orElseThrow(
            ()-> new ResourceNotFoundException("Blog not found with id: " + blogId)
        );
    }

    @Override
    public void deleteBlog(long id) {
        Optional<Blog> blog = blogRepository.findById(id);
        if(blog.isEmpty()){
            throw new ResourceNotFoundException("Blog not found with id: " + id);
        }
        blogRepository.delete(blog.get());
    }
    
}
