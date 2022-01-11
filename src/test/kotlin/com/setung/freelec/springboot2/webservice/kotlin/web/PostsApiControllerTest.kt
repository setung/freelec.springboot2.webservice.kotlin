package com.setung.freelec.springboot2.webservice.kotlin.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.setung.freelec.springboot2.webservice.kotlin.domain.posts.Posts
import com.setung.freelec.springboot2.webservice.kotlin.domain.posts.PostsRepository
import com.setung.freelec.springboot2.webservice.kotlin.web.dto.PostsSaveRequestDto
import com.setung.freelec.springboot2.webservice.kotlin.web.dto.PostsUpdateRequestDto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostsApiControllerTest(
    @Autowired val postsRepository: PostsRepository,
    @Autowired val context: WebApplicationContext,
    @LocalServerPort val port: Int
) {

    lateinit var mvc: MockMvc

    @AfterEach
    fun tearDown() = postsRepository.deleteAll()

    @BeforeEach
    fun setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
            .apply<DefaultMockMvcBuilder>(SecurityMockMvcConfigurers.springSecurity())
            .build()
    }

    @Test
    @WithMockUser(roles = ["USER"])
    @DisplayName("Posts 등록된다.")
    fun createPosts() {
        val title = "title"
        val content = "content"
        val requestDto = PostsSaveRequestDto(title, content, "author")
        val url = "http://localhost:$port/api/v1/posts"

        mvc.perform(
            post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(ObjectMapper().writeValueAsString(requestDto))
        ).andExpect(status().isOk)

        val all = postsRepository.findAll()
        assertThat(all[0].title).isEqualTo(title)
        assertThat(all[0].content).isEqualTo(content)
    }

    @Test
    @WithMockUser(roles = ["USER"])
    @DisplayName("Posts_수정된다")
    fun updatePosts() {
        val savedPosts = postsRepository.save(Posts("title", "content", "author"))

        val updateId = savedPosts.id
        val expectedTitle = "title2"
        val expectedContent = "content2"
        val requestDto = PostsUpdateRequestDto(expectedTitle, expectedContent)
        val url = "http://localhost:$port/api/v1/posts/$updateId"

        mvc.perform(
            put(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(ObjectMapper().writeValueAsString(requestDto))
        ).andExpect(status().isOk)

        val all = postsRepository.findAll()
        assertThat(all[0].title).isEqualTo(expectedTitle)
        assertThat(all[0].content).isEqualTo(expectedContent)
    }

}