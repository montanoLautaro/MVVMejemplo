package com.example.ejemplomvvm.domain

import com.example.ejemplomvvm.data.model.QuoteModel
import com.example.ejemplomvvm.data.model.QuoteProvider
import javax.inject.Inject

import kotlin.random.Random

class GetRandomQuote @Inject constructor() {
    operator fun invoke(): QuoteModel? {
        val list = QuoteProvider.quotes
        val randomInt = Random.nextInt(list.size)
        val quote = list[randomInt]
        if (quote != null) {
            return list[randomInt]
        }
        return null
    }
}