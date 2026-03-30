package com.sexadventure.domain.di

import com.sexadventure.domain.usecase.GetFavouritePosesUseCase
import com.sexadventure.domain.usecase.GetPoseByIdUseCase
import com.sexadventure.domain.usecase.GetPoseOfTheDayUseCase
import com.sexadventure.domain.usecase.GetPosesByCategoryUseCase
import com.sexadventure.domain.usecase.GetPosesCountUseCase
import com.sexadventure.domain.usecase.GetRandomPoseUseCase
import com.sexadventure.domain.usecase.SearchPosesByNameUseCase
import com.sexadventure.domain.usecase.SeedPosesUseCase
import com.sexadventure.domain.usecase.ToggleFavouriteUseCase
import com.sexadventure.domain.usecase.UpdatePersonalScoreUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCaseModule = module {
    factoryOf(::GetPoseByIdUseCase)
    factoryOf(::GetPoseOfTheDayUseCase)
    factoryOf(::GetRandomPoseUseCase)
    factoryOf(::GetPosesCountUseCase)
    factoryOf(::GetPosesByCategoryUseCase)
    factoryOf(::GetFavouritePosesUseCase)
    factoryOf(::SeedPosesUseCase)
    factoryOf(::ToggleFavouriteUseCase)
    factoryOf(::UpdatePersonalScoreUseCase)
    factoryOf(::SearchPosesByNameUseCase)
}
