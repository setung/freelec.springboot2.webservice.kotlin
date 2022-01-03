package com.setung.freelec.springboot2.webservice.kotlin.web

import com.setung.freelec.springboot2.webservice.kotlin.web.dto.HelloResponseDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @GetMapping("/hello")
    fun hello() = "hello"

    @GetMapping("/hello/dto")
    fun helloDto(@RequestParam name: String, @RequestParam amount: Int) = HelloResponseDto(name, amount)
}