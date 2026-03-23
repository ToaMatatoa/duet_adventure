package com.sexadventure.di

import com.sexadventure.domain.di.domainModules
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) = startKoin {
        config?.invoke(this)
        modules(
            *domainModules().toTypedArray(),

            viewModelModule,
        )
    }
