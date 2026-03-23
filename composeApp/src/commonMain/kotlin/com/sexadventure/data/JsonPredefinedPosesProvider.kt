package com.sexadventure.data

import com.sexadventure.domain.model.PoseData
import com.sexadventure.domain.provider.PredefinedPosesProvider
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.ExperimentalResourceApi
import sexadventure.composeapp.generated.resources.Res

/**
 * Reads `composeResources/files/predefined_poses.json` and maps
 * each entry to a [PoseData] ready for domain use.
 */
class JsonPredefinedPosesProvider : PredefinedPosesProvider {

    private val json = Json { ignoreUnknownKeys = true }

    @OptIn(ExperimentalResourceApi::class)
    override suspend fun getPoses(): List<PoseData> {
        val bytes = Res.readBytes(path = "files/predefined_poses.json")
        val jsonString = bytes.decodeToString()
        val dtos: List<PoseJson> = json.decodeFromString(jsonString)

        return dtos.map { dto ->
            PoseData(
                name = dto.name,
                description = dto.description,
                imageUrl = dto.imageUrl,
                category = dto.category,
                difficulty = dto.difficulty,
            )
        }
    }
}
