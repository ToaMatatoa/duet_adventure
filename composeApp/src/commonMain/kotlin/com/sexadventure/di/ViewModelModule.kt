package com.sexadventure.di

import com.sexadventure.presentation.allposes.AllPosesViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
        viewModelOf(constructor = ::AllPosesViewModel)
    }
