package cc.capsl.social.manager

import cc.capsl.social.domain.Session

interface SessionManager {

    suspend fun load(): Session

    suspend fun login(email: String, password: String): Session

    suspend fun register(name: String, email: String, password: String): Session
}