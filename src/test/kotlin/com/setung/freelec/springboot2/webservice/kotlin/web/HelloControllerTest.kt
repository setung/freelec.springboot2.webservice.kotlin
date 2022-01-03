package com.setung.freelec.springboot2.webservice.kotlin.web

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.*
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.*
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.hamcrest.Matchers.`is`

@WebMvcTest
class HelloControllerTest {

    @Autowired
    lateinit var mvc: MockMvc

    @Test
    fun hello가_리턴된다() {
        val hello = "hello"
        mvc.perform(get("/hello"))
            .andExpect(status().isOk)
            .andExpect(content().string(hello))
    }

    @Test
    fun helloDto가_리턴된다() {
        val name = "hello"
        val amount = 1000

        mvc.perform(
            get("/hello/dto")
                .param("name", name)
                .param("amount", amount.toString())
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.name", `is`(name)))
            .andExpect(jsonPath("$.amount", `is`(amount)))
    }
}