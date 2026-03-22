package com.sexadventure.core.di

import com.sexadventure.core.database.getPoseDao
import com.sexadventure.core.database.getRoomDatabase
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

expect fun platformModule(): Module

fun initKoin(config: KoinAppDeclaration? = null) =
    startKoin {
        config?.invoke(this)
        modules(
            platformModule(),
            provideDatabaseModule,
        )
    }

val provideDatabaseModule = module {
        single { getRoomDatabase(builder = get()) }
        single { getPoseDao(sexAdventureDatabase = get()) }
    }
