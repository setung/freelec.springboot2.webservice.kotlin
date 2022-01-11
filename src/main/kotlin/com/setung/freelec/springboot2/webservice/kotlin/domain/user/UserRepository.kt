package com.setung.freelec.springboot2.webservice.kotlin.domain.user

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository: JpaRepository<User,Long> {

    fun findByEmail(email: String): Optional<User>
}