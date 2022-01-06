package com.setung.freelec.springboot2.webservice.kotlin.service.posts

import com.setung.freelec.springboot2.webservice.kotlin.domain.posts.Posts
import com.setung.freelec.springboot2.webservice.kotlin.domain.posts.PostsRepository
import com.setung.freelec.springboot2.webservice.kotlin.web.dto.PostsListResponseDto
import com.setung.freelec.springboot2.webservice.kotlin.web.dto.PostsResponseDto
import com.setung.freelec.springboot2.webservice.kotlin.web.dto.PostsSaveRequestDto
import com.setung.freelec.springboot2.webservice.kotlin.web.dto.PostsUpdateRequestDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors

@Service
class PostsService(private val postsRepository: PostsRepository) {

    @Transactional
    fun save(requestDto: PostsSaveRequestDto): Long? {
        return postsRepository.save(requestDto.toEntity()).id
    }

    @Transactional
    fun update(id: Long, requestDto: PostsUpdateRequestDto): Long {
        val posts: Posts =
            postsRepository.findById(id).orElseThrow { IllegalArgumentException("해당 게시글이 없습니다. id= $id") }

        posts.update(requestDto.title, requestDto.content)

        return id
    }

    fun findById(id: Long): PostsResponseDto {
        val entity =
            postsRepository.findById(id).orElseThrow { IllegalArgumentException("해당 게시글이 없습니다. id= $id") }

        return PostsResponseDto(entity)
    }

    @Transactional(readOnly = true)
    fun findAllDesc(): List<PostsListResponseDto>? {
        return postsRepository.findAllDesc().stream()
            .map { entity: Posts? -> PostsListResponseDto(entity!!) }
            .collect(Collectors.toList())
    }

    @Transactional
    fun delete(id: Long) {
        val posts = postsRepository.findById(id).orElseThrow { IllegalArgumentException("해당 게시글이 없습니다. id= $id") }
        postsRepository.delete(posts)
    }
}