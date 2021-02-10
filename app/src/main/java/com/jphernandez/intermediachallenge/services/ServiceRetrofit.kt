package com.jphernandez.intermediachallenge.services

import com.jphernandez.intermediachallenge.dto.QueryResultDto
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceRetrofit {

    @GET("/v1/public/characters?")
    fun getCharactersList(@Query("offset") offset: Int = 0, @Query("limit") limit: Int = 15, @Query("apikey") apiKey: String = "3a783b25c80e1c44875356dd363f272d", @Query("hash") hash: String = "51a3ecf2f92a23817992a2663183325e", @Query("ts") ts: String = "1"): Observable<QueryResultDto>

    @GET("/v1/public/characters/{characterId}")
    fun getCharacterById(@Path("characterId") characterId: Long, @Query("apikey") apiKey: String = "3a783b25c80e1c44875356dd363f272d", @Query("hash") hash: String = "51a3ecf2f92a23817992a2663183325e", @Query("ts") ts: String = "1"): Observable<QueryResultDto>

    @GET("/v1/public/events?")
    fun getEventsList(@Query("offset") offset: Int = 0, @Query("limit") limit: Int = 25, @Query("apikey") apiKey: String = "3a783b25c80e1c44875356dd363f272d", @Query("hash") hash: String = "51a3ecf2f92a23817992a2663183325e", @Query("ts") ts: String = "1"): Observable<QueryResultDto>


    companion object {
        fun create(retrofit: Retrofit): ServiceRetrofit =
            retrofit.create(ServiceRetrofit::class.java)
    }
}