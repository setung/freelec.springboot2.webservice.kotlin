package com.setung.freelec.springboot2.webservice.kotlin.config.auth.dto

import com.setung.freelec.springboot2.webservice.kotlin.domain.user.Role
import com.setung.freelec.springboot2.webservice.kotlin.domain.user.User

class OAuthAttributes private constructor(
    val attributes: Map<String, Any>,
    val nameAttributeKey: String,
    val name: String,
    val email: String,
    val picture: String
) {
    fun toEntity(): User = User(name, email, picture, Role.USER)

    companion object {
        fun of(registrationId: String, userNameAttributeName: String, attributes: Map<String, Any>): OAuthAttributes {
            if (registrationId == "naver") {
                return ofNaver("id", attributes)
            }
            return ofGoogle(userNameAttributeName, attributes)
        }

        @Suppress("UNCHECKED_CAST")
        private fun ofNaver(userNameAttributeName: String, attributes: Map<String, Any>): OAuthAttributes {
            val response: Map<String, Any> = attributes["response"] as Map<String, Any>
            return OAuthAttributes(
                name = response["name"] as String,
                email = response["email"] as String,
                picture = response.getOrDefault("profile_image", "") as String,
                attributes = response,
                nameAttributeKey = userNameAttributeName
            )
        }

        private fun ofGoogle(userNameAttributeName: String, attributes: Map<String, Any>): OAuthAttributes {
            return OAuthAttributes(
                name = attributes["name"] as String,
                email = attributes["email"] as String,
                picture = attributes["picture"] as String,
                attributes = attributes,
                nameAttributeKey = userNameAttributeName
            )
        }

    }

}