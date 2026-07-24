package com.sexadventure.core.di

import androidx.room.RoomDatabase
import com.sexadventure.core.database.SexAdventureDatabase
import com.sexadventure.core.database.getDatabaseBuilder
import com.sexadventure.core.datasource.ImageStorage
import org.koin.dsl.module

actual fun platformModule() = module {
    single { ImageStorage() }
    single<RoomDatabase.Builder<SexAdventureDatabase>> {
        getDatabaseBuilder()
    }
}
