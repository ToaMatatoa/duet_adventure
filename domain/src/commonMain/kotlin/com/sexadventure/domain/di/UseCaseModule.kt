package com.sexadventure.domain.di

import com.sexadventure.domain.usecase.GetAllPosesUseCase
import com.sexadventure.domain.usecase.GetFavouritePosesUseCase
import com.sexadventure.domain.usecase.GetPoseByIdUseCase
import com.sexadventure.domain.usecase.SeedPosesUseCase
import com.sexadventure.domain.usecase.ToggleFavouriteUseCase
import com.sexadventure.domain.usecase.UpdatePersonalScoreUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCaseModule = module {
    factoryOf(::GetAllPosesUseCase)
    factoryOf(::GetPoseByIdUseCase)
    factoryOf(::GetFavouritePosesUseCase)
    factoryOf(::SeedPosesUseCase)
    factoryOf(::ToggleFavouriteUseCase)
    factoryOf(::UpdatePersonalScoreUseCase)
}
