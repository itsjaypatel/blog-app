package com.example.demo.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@Embeddable
public class LikeId implements Serializable {
    
    private long blogId;
    private long userId;
}
