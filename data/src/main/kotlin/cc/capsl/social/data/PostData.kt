package cc.capsl.social.data

import java.util.*

data class PostData(
        val id: String,
        val title: String,
        val content: String,
        val image: String,
        val date: Date,
        var user: UserData
)