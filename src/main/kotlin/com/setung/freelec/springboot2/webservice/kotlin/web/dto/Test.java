package com.setung.freelec.springboot2.webservice.kotlin.web.dto;

import com.setung.freelec.springboot2.webservice.kotlin.domain.posts.Posts;
import com.setung.freelec.springboot2.webservice.kotlin.domain.posts.PostsRepository;

public class Test {

    PostsRepository repository;

    void update(Long id) {
        Posts posts = repository.findById(id).orElseThrow(() -> new IllegalArgumentException(""));
    }
}
