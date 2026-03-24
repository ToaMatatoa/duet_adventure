package com.sexadventure.domain.usecase

import com.sexadventure.core.database.PoseOfTheDayEntity
import com.sexadventure.core.repository.SinglePoseRepository
import com.sexadventure.domain.mapper.toPoseData
import com.sexadventure.domain.model.PoseData
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

/**
 * Returns today's "Pose of the Day".
 *
 * Logic:
 *  1. Check the single-row `pose_of_the_day` table.
 *  2. If a row exists and its date == today → look up the full pose → return it.
 *  3. Otherwise → pick a random pose, persist its id + today's date, return it.
 */
class GetPoseOfTheDayUseCase(
    private val singlePoseRepository: SinglePoseRepository,
) {
    suspend operator fun invoke(): PoseData? {
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault()).toString()
        val existingPose = singlePoseRepository.getNecessaryInfoAboutPoseOfTheDay()

        if (existingPose != null && existingPose.date == today) {
            return singlePoseRepository.getPoseById(existingPose.poseId)?.toPoseData()
        }

        val randomPose = singlePoseRepository.getRandomPose()?.toPoseData() ?: return null

        singlePoseRepository.saveNecessaryInfoAboutPoseOfTheDay(
            entity = PoseOfTheDayEntity(poseId = randomPose.id, date = today),
        )

        return randomPose
    }
}
