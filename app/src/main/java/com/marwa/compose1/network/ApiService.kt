package com.marwa.compose1.network

import com.marwa.compose1.QuoteResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("quotes")
      suspend fun getQuotes():Response<QuoteResponse>
}