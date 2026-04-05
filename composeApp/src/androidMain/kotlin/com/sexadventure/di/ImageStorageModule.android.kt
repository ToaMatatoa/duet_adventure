package com.sexadventure.di

import com.sexadventure.storage.ImageStorage
import org.koin.core.module.Module

actual fun Module.provideImageStorage() {
    single { ImageStorage(context = get()) }
}
