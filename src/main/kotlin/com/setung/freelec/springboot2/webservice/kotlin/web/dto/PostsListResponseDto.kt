package com.setung.freelec.springboot2.webservice.kotlin.web.dto

import com.setung.freelec.springboot2.webservice.kotlin.domain.posts.Posts
import java.time.LocalDateTime

data class PostsListResponseDto(
    val id: Long?,
    val title: String?,
    val author: String?,
    val modifiedDate: LocalDateTime?
) {
    constructor(entity: Posts) : this(entity.id, entity.title, entity.author, entity.modifiedDate)
}
