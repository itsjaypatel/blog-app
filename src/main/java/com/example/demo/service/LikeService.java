package com.example.demo.service;


import com.example.demo.entity.Like;
import com.example.demo.entity.LikeId;

import java.util.List;

public interface LikeService {

    void likeBlog(LikeId id);

    void dislikeBlog(LikeId id);

    List<Like> getLikesByBlogId(long blogId);
}
