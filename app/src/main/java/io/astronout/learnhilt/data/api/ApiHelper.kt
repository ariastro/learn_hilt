package io.astronout.learnhilt.data.api

import io.astronout.learnhilt.data.model.User
import retrofit2.Response

interface ApiHelper {

    suspend fun getUsers(): Response<List<User>>

}