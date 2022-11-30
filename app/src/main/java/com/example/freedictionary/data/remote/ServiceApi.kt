package com.example.freedictionary.data.remote

import com.example.freedictionary.data.remote.model.WordDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ServiceApi {

    @GET("entries/en/{word}")
    suspend fun getWord(
        @Path("word") word: String
    ): List<WordDto>

}