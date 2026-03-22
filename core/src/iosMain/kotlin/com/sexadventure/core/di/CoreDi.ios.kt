package com.sexadventure.core.di

import androidx.room.RoomDatabase
import com.sexadventure.core.database.SexAdventureDatabase
import com.sexadventure.core.database.getDatabaseBuilder
import org.koin.dsl.module

actual fun platformModule() = module {
        single<RoomDatabase.Builder<SexAdventureDatabase>> {
            getDatabaseBuilder()
        }
    }
