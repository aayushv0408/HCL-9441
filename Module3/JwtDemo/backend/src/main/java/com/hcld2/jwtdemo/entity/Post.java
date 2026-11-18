package com.hcld2.jwtdemo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(length = 2000)
    private String content;
    @Column(nullable = false)
    private String author;
    private LocalDateTime createdAt=LocalDateTime.now();

    public Post(String title, String content, String author) {
        this.title = title;
        this.author=author;
        this.content = content;
    }
}
