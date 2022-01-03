package com.setung.freelec.springboot2.webservice.kotlin.web.dto

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class HelloResponseDtoTest {

    @Test
    fun data_class_test() {
        val name = "test"
        val amount = 1000;

        val dto = HelloResponseDto(name,amount)

        Assertions.assertThat(dto.name).isEqualTo(name)
        Assertions.assertThat(dto.amount).isEqualTo(amount)
    }
}