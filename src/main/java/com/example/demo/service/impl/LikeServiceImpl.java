package com.example.demo.service.impl;

import com.example.demo.entity.Like;
import com.example.demo.entity.LikeId;
import com.example.demo.exception.ResourceConflictException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.LikeRepository;
import com.example.demo.service.LikeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;

    public LikeServiceImpl(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @Override
    public void likeBlog(LikeId id) {
        if (likeRepository.existsById(id)) {
            throw new ResourceConflictException("User " + id.getUserId()  + " already liked blog " + id.getBlogId());
        }
        likeRepository.save(new Like(id));
    }

    @Override
    public void dislikeBlog(LikeId id) {
        Optional<Like> like = likeRepository.findById(id);
        if(like.isEmpty()){
            throw new ResourceNotFoundException("Like Not Found for blog " + id + " by user " + id);
        }
        likeRepository.delete(like.get());
    }

    @Override
    public List<Like> getLikesByBlogId(long blogId) {
        return likeRepository.findByIdBlogId(blogId);
    }
}
