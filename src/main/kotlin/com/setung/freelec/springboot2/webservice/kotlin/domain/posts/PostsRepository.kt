package com.setung.freelec.springboot2.webservice.kotlin.domain.posts

import org.springframework.data.jpa.repository.JpaRepository

interface PostsRepository : JpaRepository<Posts, Long> {
}