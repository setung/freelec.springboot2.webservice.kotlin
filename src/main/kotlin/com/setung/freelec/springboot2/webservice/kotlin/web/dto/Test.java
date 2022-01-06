package com.setung.freelec.springboot2.webservice.kotlin.web.dto;

import com.setung.freelec.springboot2.webservice.kotlin.domain.posts.Posts;
import com.setung.freelec.springboot2.webservice.kotlin.domain.posts.PostsRepository;

import java.util.List;
import java.util.stream.Collectors;

public class Test {

    PostsRepository postsRepository;

    void update(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(""));
    }

    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
