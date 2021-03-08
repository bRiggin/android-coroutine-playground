package com.briggin.coroutineplayground

import com.briggin.coroutineplayground.api.ApiService
import com.briggin.coroutineplayground.api.BASE_URL
import com.briggin.coroutineplayground.api.PlaygroundApi
import com.briggin.coroutineplayground.api.PlaygroundApiService
import com.briggin.coroutineplayground.presentation.PlaygroundViewModel
import com.google.gson.GsonBuilder
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val koinModule = module {

    viewModel { PlaygroundViewModel(get()) }

    factory<ApiService> { PlaygroundApiService(get()) }

    factory<PlaygroundApi> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(PlaygroundApi::class.java)
    }
}