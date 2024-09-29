package com.marwa.compose1.network

import com.marwa.compose1.QuoteModel
import com.marwa.compose1.QuoteResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("quotes")
    suspend fun getQuotes(): Response<QuoteResponse>

    @GET("quotes/{id}")
    suspend fun getQuote(@Path("id") id: String): Response<QuoteModel>
}