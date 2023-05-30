package com.example.ejemplomvvm.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ejemplomvvm.data.model.QuoteModel
import com.example.ejemplomvvm.domain.GetQuotesUseCase
import com.example.ejemplomvvm.domain.GetRandomQuote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// HiltViewModel prepara al viewmodel para inyectarle dependencias
@HiltViewModel
//dentro del constructor ponemos las clases que van a ser inyectadas a esta clase
//@Inject nos permite inyectar esta clase en otra
class QuoteViewModel @Inject constructor(
    private val getQuotesUseCase : GetQuotesUseCase,
    private val getRandomQuote : GetRandomQuote
) : ViewModel() {

    val quoteModel = MutableLiveData<QuoteModel>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result: List<QuoteModel>? = getQuotesUseCase()
            if (!result.isNullOrEmpty()) {
                quoteModel.postValue(result[0])
            }
            isLoading.postValue(false)
        }
    }

    fun randomQuote(){
        isLoading.postValue(true)
        val quote : QuoteModel? = getRandomQuote()
        if (quote != null){
            quoteModel.postValue(quote)
        }
        isLoading.postValue(false)
    }


}