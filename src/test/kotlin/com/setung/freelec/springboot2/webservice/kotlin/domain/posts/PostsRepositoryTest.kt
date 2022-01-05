package com.setung.freelec.springboot2.webservice.kotlin.domain.posts

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class PostsRepositoryTest {

    @Autowired
    lateinit var postsRepository: PostsRepository

    @AfterEach
    fun cleanUp() {
        postsRepository.deleteAll()
    }

    @Test
    fun 게시글_불러오기() {
        val title = "테스트 게시글"
        val content = "테스트 본문"

        postsRepository.save(Posts(title, content, "jsh@gmail.com"))

        val postsList = postsRepository.findAll()

        val posts = postsList.get(0)
        assertThat(posts.title).isEqualTo(title)
        assertThat(posts.content).isEqualTo(content)
    }

    @Test
    @DisplayName("BaseTimeEntity 등록")
    fun testBaseTimeEntity() {
        val now = LocalDateTime.of(2022, 1, 6, 0, 0, 0)
        postsRepository.save(Posts("title", "content", "author"))

        val postsList = postsRepository.findAll()
        val posts = postsList[0]

        println(">>>>>>>>>>> createdDate= ${posts.createdDate}, modifiedDate= ${posts.modifiedDate}")
        assertThat(posts.createdDate).isAfter(now)
        assertThat(posts.modifiedDate).isAfter(now)
    }
}
