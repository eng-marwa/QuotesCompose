package com.marwa.compose1.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

  private fun getRetrofit(): Retrofit {
    return Retrofit.Builder()
      .baseUrl("http://api.quotable.io/")
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}