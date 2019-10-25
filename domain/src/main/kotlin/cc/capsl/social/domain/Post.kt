package cc.capsl.social.domain

import java.util.*

data class Post(
        val id: String,
        val title: String,
        val content: String,
        val image: String,
        val date: Date,
        var user: User
)