package com.setung.freelec.springboot2.webservice.kotlin.domain.user

enum class Role(val key: String, val title: String) {
    GUEST("ROLE_GUEST", "손님"), USER("ROLE_USER", "일반 사용자");
}
