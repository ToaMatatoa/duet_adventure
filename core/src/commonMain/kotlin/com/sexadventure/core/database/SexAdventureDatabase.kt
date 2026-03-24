@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.sexadventure.core.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Database(entities = [PoseEntity::class, PoseOfTheDayEntity::class], version = 1)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class SexAdventureDatabase : RoomDatabase() {
    abstract fun getPoseDao(): PoseDao

    abstract fun getPoseOfTheDayDao(): PoseOfTheDayDao
}

// The Room compiler generates the `actual` implementations.
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<SexAdventureDatabase> {
    override fun initialize(): SexAdventureDatabase
}

fun getRoomDatabase(builder: RoomDatabase.Builder<SexAdventureDatabase>): SexAdventureDatabase =
    builder
        .fallbackToDestructiveMigration(true)
        .fallbackToDestructiveMigrationOnDowngrade(true)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()

fun getPoseDao(sexAdventureDatabase: SexAdventureDatabase) = sexAdventureDatabase.getPoseDao()

fun getPoseOfTheDayDao(sexAdventureDatabase: SexAdventureDatabase) = sexAdventureDatabase.getPoseOfTheDayDao()
