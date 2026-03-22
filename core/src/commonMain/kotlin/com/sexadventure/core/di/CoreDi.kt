package com.sexadventure.core.di

import com.sexadventure.core.database.getPoseDao
import com.sexadventure.core.database.getRoomDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

expect fun platformModule(): Module

val provideDatabaseModule = module {
        single { getRoomDatabase(builder = get()) }
        single { getPoseDao(sexAdventureDatabase = get()) }
    }
