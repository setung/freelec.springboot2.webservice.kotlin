package com.setung.freelec.springboot2.webservice.kotlin.web

import com.setung.freelec.springboot2.webservice.kotlin.domain.posts.Posts
import com.setung.freelec.springboot2.webservice.kotlin.domain.posts.PostsRepository
import com.setung.freelec.springboot2.webservice.kotlin.web.dto.PostsSaveRequestDto
import com.setung.freelec.springboot2.webservice.kotlin.web.dto.PostsUpdateRequestDto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.exchange
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostsApiControllerTest(
    @Autowired val restTemplate: TestRestTemplate,
    @Autowired val postsRepository: PostsRepository,
    @LocalServerPort val port: Int
) {

    @AfterEach
    fun tearDown() = postsRepository.deleteAll()

    @Test
    @DisplayName("Posts 등록된다.")
    fun createPosts() {
        val title = "title"
        val content = "content"
        val requestDto = PostsSaveRequestDto(title, content, "author")
        val url = "http://localhost:$port/api/v1/posts"

        val responseEntity: ResponseEntity<Long> = restTemplate.postForEntity(url, requestDto, Long)

        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(responseEntity.body).isGreaterThan(0L)

        val all = postsRepository.findAll()

        assertThat(all[0].title).isEqualTo(title)
        assertThat(all[0].content).isEqualTo(content)
    }

    @Test
    @DisplayName("Posts_수정된다")
    fun updatePosts() {
        val savedPosts = postsRepository.save(Posts("title", "content", "author"))

        val updateId = savedPosts.id
        val expectedTitle = "title2"
        val expectedContent = "content2"
        val requestDto = PostsUpdateRequestDto(expectedTitle, expectedContent)
        val url = "http://localhost:$port/api/v1/posts/$updateId"

        val requestEntity = HttpEntity<PostsUpdateRequestDto>(requestDto)

        val responseEntity: ResponseEntity<Long> = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long)

        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(responseEntity.body).isGreaterThan(0L)
        val all = postsRepository.findAll()
        assertThat(all[0].title).isEqualTo(expectedTitle)
        assertThat(all[0].content).isEqualTo(expectedContent)
    }


}