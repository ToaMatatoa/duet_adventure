package com.sexadventure.core.di

import com.sexadventure.core.database.getPoseDao
import com.sexadventure.core.database.getPoseOfTheDayDao
import com.sexadventure.core.database.getRoomDatabase
import com.sexadventure.core.repository.PoseOfTheDayRepository
import com.sexadventure.core.repository.PoseOfTheDayRepositoryImpl
import com.sexadventure.core.repository.PoseRepository
import com.sexadventure.core.repository.PoseRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

expect fun platformModule(): Module

val provideDatabaseModule = module {
        single { getRoomDatabase(builder = get()) }
        single { getPoseDao(sexAdventureDatabase = get()) }
        single { getPoseOfTheDayDao(sexAdventureDatabase = get()) }
        single<PoseRepository> { PoseRepositoryImpl(poseDao = get()) }
        single<PoseOfTheDayRepository> { PoseOfTheDayRepositoryImpl(dao = get()) }
    }

/**
 * All Koin modules needed by the core layer
 */
val coreModules = listOf(
    provideDatabaseModule,
)
