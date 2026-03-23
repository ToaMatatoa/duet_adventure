package com.sexadventure.di

import com.sexadventure.presentation.allposes.AllPosesViewModel
import com.sexadventure.presentation.detailpose.DetailPoseViewModel
import com.sexadventure.presentation.favouriteposes.FavouritePosesViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(constructor = ::AllPosesViewModel)
    viewModelOf(constructor = ::FavouritePosesViewModel)
    viewModelOf(constructor = ::DetailPoseViewModel)
}
