package com.setung.freelec.springboot2.webservice.kotlin.web

import com.setung.freelec.springboot2.webservice.kotlin.service.posts.PostsService
import com.setung.freelec.springboot2.webservice.kotlin.web.dto.PostsSaveRequestDto
import com.setung.freelec.springboot2.webservice.kotlin.web.dto.PostsUpdateRequestDto
import org.springframework.web.bind.annotation.*

@RestController
class PostsApiController(private val postsService: PostsService) {

    @PostMapping("/api/v1/posts")
    fun save(@RequestBody requestDto: PostsSaveRequestDto) = postsService.save(requestDto)

    @PutMapping("/api/v1/posts/{id}")
    fun update(@PathVariable id: Long, @RequestBody requestDto: PostsUpdateRequestDto) =
        postsService.update(id, requestDto)

    @GetMapping("/api/v1/posts/{id}")
    fun findById(@PathVariable id: Long) = postsService.findById(id)

    @DeleteMapping("/api/v1/posts/{id}")
    fun delete(@PathVariable id: Long): Long {
        postsService.delete(id)
        return id
    }
}