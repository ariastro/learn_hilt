package io.astronout.learnhilt.data.repository

import io.astronout.learnhilt.data.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getUsers() =  apiHelper.getUsers()

}