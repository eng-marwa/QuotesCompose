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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    var state by mutableStateOf(emptyList<QuoteModel>())
    lateinit var response: Response<QuoteResponse>
    private var apiService: ApiService = RetrofitClient.apiService

    init {
        getQuotes()
    }

    private fun getQuotes() {
        viewModelScope.launch {
            response = apiService.getQuotes()
            if (response.isSuccessful) {
                state = response.body()?.quotes?.restoreFavoriteQuotes() ?: emptyList()
            }
        }

    }



    fun toggleFavorite(id: String) {
        val quotesList = state.toMutableList()
        val index = quotesList.indexOfFirst { it.id == id }
        quotesList[index] =
            quotesList[index].copy(isFavorite = !quotesList[index].isFavorite)
        storeFavoriteQuotes(quotesList[index])
        state = quotesList

    }

    private fun storeFavoriteQuotes(quote: QuoteModel) {
        val savedFavoriteList =
            savedStateHandle.get<List<String>?>("favorite_quotes").orEmpty().toMutableList()
        if (quote.isFavorite) {
            savedFavoriteList.add(quote.id)
        } else {
            savedFavoriteList.remove(quote.id)
        }
        savedStateHandle["favorite_quotes"] = savedFavoriteList
    }

    private fun List<QuoteModel>.restoreFavoriteQuotes(): List<QuoteModel> {
        savedStateHandle.get<List<String>>("favorite_quotes")?.let { savedIds ->
            savedIds.forEach { quoteId -> this.find { it.id == quoteId }?.isFavorite = true }
        }

        return this
    }
}