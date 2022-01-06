package com.setung.freelec.springboot2.webservice.kotlin.web

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForObject

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class IndexControllerTest(@Autowired val restTemplate: TestRestTemplate) {

    @Test
    @DisplayName("메인페이지 로딩")
    fun loadingMainPage() {
        val body: String? = restTemplate.getForObject("/", String)

        Assertions.assertThat(body).contains("스프링 부트로 시작하는 웹 서비스")
    }
}