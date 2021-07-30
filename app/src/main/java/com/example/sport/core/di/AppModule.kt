package com.example.core.di

import com.example.core.domain.usecase.SportInteractor
import com.example.core.domain.usecase.SportUseCase
import com.example.sport.core.detail.DetailSportViewModel
import com.example.sport.core.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<SportUseCase> { SportInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailSportViewModel(get()) }
}