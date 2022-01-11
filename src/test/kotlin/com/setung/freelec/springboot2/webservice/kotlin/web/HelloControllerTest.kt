package com.setung.freelec.springboot2.webservice.kotlin.web

import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType
import org.springframework.security.access.SecurityConfig
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*


@WebMvcTest(
    controllers = [HelloController::class],
    excludeFilters = [ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = arrayOf(SecurityConfig::class))]
)
@SpringBootTest
class HelloControllerTest( private var mvc: MockMvc) {

    @WithMockUser(roles = ["USER"])
    @Test
    fun hello가_리턴된다() {
        val hello = "hello"
        mvc!!.perform(get("/hello"))
            .andExpect(status().isOk)
            .andExpect(content().string(hello))
    }

    @WithMockUser(roles = ["USER"])
    @Test
    fun helloDto가_리턴된다() {
        val name = "hello"
        val amount = 1000
        mvc!!.perform(
            get("/hello/dto")
                .param("name", name)
                .param("amount", amount.toString())
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.name", `is`(name)))
            .andExpect(jsonPath("$.amount", `is`(amount)))
    }
}