package com.hcld2.jwtdemo.service;

import com.hcld2.jwtdemo.dto.PostRequest;
import com.hcld2.jwtdemo.dto.PostResponse;
import com.hcld2.jwtdemo.entity.Post;
import com.hcld2.jwtdemo.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository posts;

    @Transactional(readOnly = true)
    public List<PostResponse> findAll() {
        return posts.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(PostResponse::from)
                .toList();
    }


    @Transactional
    public PostResponse addPost(PostRequest req, String username) {
        Post saved = posts.save(new Post(req.title(), req.content(), username));
        return PostResponse.from(saved);
    }
}
