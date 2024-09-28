package com.marwa.compose1

import com.google.gson.annotations.SerializedName


data class QuoteResponse(@SerializedName("results") val quotes: List<QuoteModel>)

data class QuoteModel(@SerializedName("_id") val id:String,@SerializedName("content") val content: String,@SerializedName("author") val author: String, var isFavorite:Boolean =false)
