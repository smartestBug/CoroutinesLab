package dev.msemyak.coroutineslab.di

import dev.msemyak.coroutineslab.ui.main.MainPresenter
import org.koin.dsl.module.module

val networkModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit( get() )}
    single { provideNetworkService( get() )}
}

val presenterModule = module {
    single { MainPresenter(get()) }
}