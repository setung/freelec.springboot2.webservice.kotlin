package com.setung.freelec.springboot2.webservice.kotlin.web

import com.setung.freelec.springboot2.webservice.kotlin.service.posts.PostsService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@Controller
class IndexController(val postsService: PostsService) {


    @GetMapping
    fun index(model: Model): String {
        model.addAttribute("posts", postsService.findAllDesc())
        return "index"
    }

    @GetMapping("/posts/save")
    fun postsSave() = "posts-save"

    @GetMapping("/posts/update/{id}")
    fun postsUpdate(@PathVariable id: Long, model: Model): String {
        val dto = postsService.findById(id)
        model.addAttribute("post",dto)
        return "posts-update"
    }
}