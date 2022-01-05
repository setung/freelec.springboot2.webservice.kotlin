package com.setung.freelec.springboot2.webservice.kotlin.web.dto

import com.setung.freelec.springboot2.webservice.kotlin.domain.posts.Posts

data class PostsResponseDto(
    private var id: Long?,
    private var title: String?,
    private var content: String?,
    private var author: String?
) {

    constructor(entity: Posts) : this(entity.id, entity.title, entity.content, entity.author)
}
