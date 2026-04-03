package com.sexadventure.di

import com.sexadventure.data.JsonPredefinedPosesProvider
import com.sexadventure.domain.provider.PredefinedPosesProvider
import org.koin.dsl.module

val dataModule = module {
    single<PredefinedPosesProvider> { JsonPredefinedPosesProvider() }
    provideImageStorage()
}
