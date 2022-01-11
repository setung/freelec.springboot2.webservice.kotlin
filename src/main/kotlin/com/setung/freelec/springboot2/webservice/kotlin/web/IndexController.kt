package com.setung.freelec.springboot2.webservice.kotlin.web

import com.setung.freelec.springboot2.webservice.kotlin.config.auth.LoginUser
import com.setung.freelec.springboot2.webservice.kotlin.config.auth.dto.SessionUser
import com.setung.freelec.springboot2.webservice.kotlin.domain.user.User
import com.setung.freelec.springboot2.webservice.kotlin.service.posts.PostsService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import javax.servlet.http.HttpSession

@Controller
class IndexController(val postsService: PostsService, val httpSession: HttpSession) {


    @GetMapping
    fun index(model: Model, @LoginUser user: SessionUser?): String {
        model.addAttribute("posts", postsService.findAllDesc())

        if (user != null)
            model.addAttribute("userName", user.name)

        return "index"
    }

    @GetMapping("/posts/save")
    fun postsSave() = "posts-save"

    @GetMapping("/posts/update/{id}")
    fun postsUpdate(@PathVariable id: Long, model: Model): String {
        val dto = postsService.findById(id)
        model.addAttribute("post", dto)
        return "posts-update"
    }
}