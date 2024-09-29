package com.marwa.compose1

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marwa.compose1.network.ApiService
import com.marwa.compose1.network.RetrofitClient
import kotlinx.coroutines.launch

class DetailsViewModel(private val vieStateHandle: SavedStateHandle) : ViewModel() {
    var state by mutableStateOf<QuoteModel?>(null)
    private var apiService: ApiService = RetrofitClient.apiService

    init {
        getQuoteDetails(vieStateHandle.get<String>("quoteId") ?: "")
    }

    private fun getQuoteDetails(id: String) {
        viewModelScope.launch {
            val response = apiService.getQuote(id)
            if (response.isSuccessful) {
                val quote = response.body()
                state = quote
                //  vieStateHandle["quote"] = quote
            }
        }
    }

}