package com.setung.freelec.springboot2.webservice.kotlin.domain.posts

import com.setung.freelec.springboot2.webservice.kotlin.domain.BaseTimeEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Posts : BaseTimeEntity {

    @Id
    @GeneratedValue
    var id: Long? = null

    @Column(length = 500, nullable = false)
    var title: String? = null

    @Column(columnDefinition = "TEXT", nullable = false)
    var content: String? = null

    var author: String? = null

    constructor(title: String?, content: String?, author: String?) {
        this.title = title
        this.content = content
        this.author = author
    }

    constructor()

    fun update(title: String?, content: String?) {
        this.title = title
        this.content = content
    }

}