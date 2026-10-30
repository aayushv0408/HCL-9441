package com.hcld2.jwtdemo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity 
@Table (name = "posts")
@NoArgsConstructor 
@AllArgsConstructor

public class Post {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (nullable = false)
    private String title;
    @Column (nullable = false)
    private String content;
    @Column (nullable = false)
    private String author;

    
}
