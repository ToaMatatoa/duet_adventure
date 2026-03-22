package com.sexadventure

import com.sexadventure.core.di.platformModule
import com.sexadventure.core.di.provideDatabaseModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) =
    startKoin {
        config?.invoke(this)
        modules(
            platformModule(),
            provideDatabaseModule,
        )
    }
