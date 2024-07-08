package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "likes")
@NoArgsConstructor
@AllArgsConstructor
public class Like {

    @EmbeddedId
    private LikeId id;
}


