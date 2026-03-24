package com.sexadventure.core.di

import com.sexadventure.core.database.getPoseDao
import com.sexadventure.core.database.getPoseOfTheDayDao
import com.sexadventure.core.database.getRoomDatabase
import com.sexadventure.core.datasource.MutablePoseDataSource
import com.sexadventure.core.datasource.MutablePoseDataSourceImpl
import com.sexadventure.core.datasource.PersistentPoseDataSource
import com.sexadventure.core.datasource.PersistentPoseDataSourceImpl
import com.sexadventure.core.repository.MultiplePoseRepository
import com.sexadventure.core.repository.MultiplePoseRepositoryImpl
import com.sexadventure.core.repository.PoseAmountRepository
import com.sexadventure.core.repository.PoseAmountRepositoryImpl
import com.sexadventure.core.repository.SinglePoseRepository
import com.sexadventure.core.repository.SinglePoseRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

expect fun platformModule(): Module

val provideDatabaseModule =
    module {
        single { getRoomDatabase(builder = get()) }
        single { getPoseDao(sexAdventureDatabase = get()) }
        single { getPoseOfTheDayDao(sexAdventureDatabase = get()) }
    }

val provideDataSourceModule =
    module {
        single<PersistentPoseDataSource> {
            PersistentPoseDataSourceImpl(poseDao = get(), poseOfTheDayDao = get())
        }
        single<MutablePoseDataSource> {
            MutablePoseDataSourceImpl(
                poseDao = get(),
                poseOfTheDayDao = get(),
            )
        }
    }

val provideRepositoryModule =
    module {
        single<MultiplePoseRepository> {
            MultiplePoseRepositoryImpl(
                persistentPoseDataSource = get(),
                mutablePoseDataSource = get(),
            )
        }
        single<PoseAmountRepository> {
            PoseAmountRepositoryImpl(
                persistentPoseDataSource = get(),
            )
        }
        single<SinglePoseRepository> {
            SinglePoseRepositoryImpl(
                persistentPoseDataSource = get(),
                mutablePoseDataSource = get(),
            )
        }
    }

/**
 * All Koin modules needed by the core layer
 */
val coreModules =
    listOf(
        provideDatabaseModule,
        provideDataSourceModule,
        provideRepositoryModule,
    )
