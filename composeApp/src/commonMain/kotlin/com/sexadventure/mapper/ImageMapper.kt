package com.sexadventure.mapper

import org.jetbrains.compose.resources.DrawableResource
import sexadventure.composeapp.generated.resources.Res
import sexadventure.composeapp.generated.resources.butter_churner
import sexadventure.composeapp.generated.resources.cowgirl
import sexadventure.composeapp.generated.resources.deep_throat
import sexadventure.composeapp.generated.resources.doggy_style
import sexadventure.composeapp.generated.resources.face_sitting
import sexadventure.composeapp.generated.resources.face_to_face
import sexadventure.composeapp.generated.resources.lazy_dog
import sexadventure.composeapp.generated.resources.leap_frog
import sexadventure.composeapp.generated.resources.lotus
import sexadventure.composeapp.generated.resources.missionary
import sexadventure.composeapp.generated.resources.oral_classic
import sexadventure.composeapp.generated.resources.pretzel
import sexadventure.composeapp.generated.resources.pretzel_dip
import sexadventure.composeapp.generated.resources.reverse_cowgirl
import sexadventure.composeapp.generated.resources.rocking_horse
import sexadventure.composeapp.generated.resources.scissors
import sexadventure.composeapp.generated.resources.shower_standing
import sexadventure.composeapp.generated.resources.side_by_side
import sexadventure.composeapp.generated.resources.sixty_nine
import sexadventure.composeapp.generated.resources.spooning
import sexadventure.composeapp.generated.resources.standing
import sexadventure.composeapp.generated.resources.standing_oral
import sexadventure.composeapp.generated.resources.tabletop
import sexadventure.composeapp.generated.resources.the_amazon
import sexadventure.composeapp.generated.resources.the_anvil
import sexadventure.composeapp.generated.resources.the_bridge
import sexadventure.composeapp.generated.resources.the_crab
import sexadventure.composeapp.generated.resources.the_flatiron
import sexadventure.composeapp.generated.resources.the_plow
import sexadventure.composeapp.generated.resources.the_rider
import sexadventure.composeapp.generated.resources.the_seashell
import sexadventure.composeapp.generated.resources.the_spider
import sexadventure.composeapp.generated.resources.the_throne
import sexadventure.composeapp.generated.resources.the_x_position
import sexadventure.composeapp.generated.resources.wheelbarrow

/**
 * Maps a pose image name (stored in JSON / Room) to a local [DrawableResource].
 *
 * The image name in the database should match the file name in
 * `composeApp/src/commonMain/composeResources/drawable/` without the extension.
 *
 * Example:
 * - DB stores: `"sixty_nine"`
 * - File: `composeResources/drawable/sixty_nine.webp`
 * - Returns: `Res.drawable.sixty_nine`
 *
 * When you add a new image:
 * 1. Drop the `.webp` file into `composeResources/drawable/`
 * 2. Add a mapping entry below
 */
private val imageMap: Map<String, DrawableResource> =
    mapOf(
        "missionary" to Res.drawable.missionary,
        "cowgirl" to Res.drawable.cowgirl,
        "reverse_cowgirl" to Res.drawable.reverse_cowgirl,
        "doggy_style" to Res.drawable.doggy_style,
        "spooning" to Res.drawable.spooning,
        "sixty_nine" to Res.drawable.sixty_nine,
        "lotus" to Res.drawable.lotus,
        "standing" to Res.drawable.standing,
        "the_bridge" to Res.drawable.the_bridge,
        "pretzel" to Res.drawable.pretzel,
        "wheelbarrow" to Res.drawable.wheelbarrow,
        "face_sitting" to Res.drawable.face_sitting,
        "the_throne" to Res.drawable.the_throne,
        "lazy_dog" to Res.drawable.lazy_dog,
        "the_spider" to Res.drawable.the_spider,
        "oral_classic" to Res.drawable.oral_classic,
        "deep_throat" to Res.drawable.deep_throat,
        "the_amazon" to Res.drawable.the_amazon,
        "butter_churner" to Res.drawable.butter_churner,
        "scissors" to Res.drawable.scissors,
        "the_seashell" to Res.drawable.the_seashell,
        "standing_oral" to Res.drawable.standing_oral,
        "the_plow" to Res.drawable.the_plow,
        "tabletop" to Res.drawable.tabletop,
        "pretzel_dip" to Res.drawable.pretzel_dip,
        "shower_standing" to Res.drawable.shower_standing,
        "the_flatiron" to Res.drawable.the_flatiron,
        "leap_frog" to Res.drawable.leap_frog,
        "the_rider" to Res.drawable.the_rider,
        "face_to_face" to Res.drawable.face_to_face,
        "the_crab" to Res.drawable.the_crab,
        "side_by_side" to Res.drawable.side_by_side,
        "the_x_position" to Res.drawable.the_x_position,
        "rocking_horse" to Res.drawable.rocking_horse,
        "the_anvil" to Res.drawable.the_anvil,
    )

/**
 * Resolves a pose image name to a [DrawableResource].
 * Returns `null` if the name is blank or not found → ListItem shows placeholder.
 */
fun resolveImage(imageName: String?): DrawableResource? {
    if (imageName.isNullOrBlank()) return null
    return imageMap[imageName]
}
