package com.hcld2.jwtdemo.controller;

import com.hcld2.jwtdemo.dto.PostRequest;
import com.hcld2.jwtdemo.dto.PostResponse;
import com.hcld2.jwtdemo.service.PostService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")

@AllArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    public List<PostResponse> all() {
        return postService.findAll();
    }

    @PostMapping
    @SecurityRequirement(name="jwt-demo-api")
    public ResponseEntity<PostResponse> create(@Valid @RequestBody PostRequest req, Authentication auth) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postService.addPost(req, auth.getName()));
    }
}
