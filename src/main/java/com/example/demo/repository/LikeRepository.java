package com.example.demo.repository;

import com.example.demo.entity.Like;
import com.example.demo.entity.LikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, LikeId> {

    List<Like> findByIdBlogId (long blogId);
}
