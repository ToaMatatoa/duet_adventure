package com.sexadventure.mapper

import org.jetbrains.compose.resources.DrawableResource
import sexadventure.composeapp.generated.resources.Res
import sexadventure.composeapp.generated.resources.ballet_dancer
import sexadventure.composeapp.generated.resources.bodyguard
import sexadventure.composeapp.generated.resources.butter_churner
import sexadventure.composeapp.generated.resources.couch_surfer
import sexadventure.composeapp.generated.resources.cowgirl
import sexadventure.composeapp.generated.resources.deep_throat
import sexadventure.composeapp.generated.resources.doggy_style
import sexadventure.composeapp.generated.resources.face_sitting
import sexadventure.composeapp.generated.resources.face_to_face
import sexadventure.composeapp.generated.resources.heir_to_the_throne
import sexadventure.composeapp.generated.resources.kneeling_oral
import sexadventure.composeapp.generated.resources.lazy_dog
import sexadventure.composeapp.generated.resources.lazy_lady
import sexadventure.composeapp.generated.resources.lazy_sunday
import sexadventure.composeapp.generated.resources.leap_frog
import sexadventure.composeapp.generated.resources.leg_glider
import sexadventure.composeapp.generated.resources.lotus
import sexadventure.composeapp.generated.resources.missionary
import sexadventure.composeapp.generated.resources.oral_classic
import sexadventure.composeapp.generated.resources.oral_side_lying
import sexadventure.composeapp.generated.resources.pinball_wizard
import sexadventure.composeapp.generated.resources.pretzel
import sexadventure.composeapp.generated.resources.pretzel_dip
import sexadventure.composeapp.generated.resources.reverse_cowgirl
import sexadventure.composeapp.generated.resources.reverse_missionary
import sexadventure.composeapp.generated.resources.rocking_horse
import sexadventure.composeapp.generated.resources.scissors
import sexadventure.composeapp.generated.resources.shoulder_holder
import sexadventure.composeapp.generated.resources.shower_standing
import sexadventure.composeapp.generated.resources.side_by_side
import sexadventure.composeapp.generated.resources.sixty_nine
import sexadventure.composeapp.generated.resources.sofa_rider
import sexadventure.composeapp.generated.resources.speed_bump
import sexadventure.composeapp.generated.resources.spooning
import sexadventure.composeapp.generated.resources.standing
import sexadventure.composeapp.generated.resources.standing_from_behind
import sexadventure.composeapp.generated.resources.standing_oral
import sexadventure.composeapp.generated.resources.suspended_congress
import sexadventure.composeapp.generated.resources.tabletop
import sexadventure.composeapp.generated.resources.teaspooning
import sexadventure.composeapp.generated.resources.the_amazon
import sexadventure.composeapp.generated.resources.the_anvil
import sexadventure.composeapp.generated.resources.the_backbend
import sexadventure.composeapp.generated.resources.the_bridge
import sexadventure.composeapp.generated.resources.the_caboose
import sexadventure.composeapp.generated.resources.the_crab
import sexadventure.composeapp.generated.resources.the_cradle
import sexadventure.composeapp.generated.resources.the_cross
import sexadventure.composeapp.generated.resources.the_deck_chair
import sexadventure.composeapp.generated.resources.the_flatiron
import sexadventure.composeapp.generated.resources.the_g_whiz
import sexadventure.composeapp.generated.resources.the_helicopter
import sexadventure.composeapp.generated.resources.the_jockey
import sexadventure.composeapp.generated.resources.the_plow
import sexadventure.composeapp.generated.resources.the_queening
import sexadventure.composeapp.generated.resources.the_rider
import sexadventure.composeapp.generated.resources.the_seashell
import sexadventure.composeapp.generated.resources.the_slip
import sexadventure.composeapp.generated.resources.the_snake
import sexadventure.composeapp.generated.resources.the_spider
import sexadventure.composeapp.generated.resources.the_splitter
import sexadventure.composeapp.generated.resources.the_stairway
import sexadventure.composeapp.generated.resources.the_throne
import sexadventure.composeapp.generated.resources.the_valedictorian
import sexadventure.composeapp.generated.resources.the_victory
import sexadventure.composeapp.generated.resources.the_x_position
import sexadventure.composeapp.generated.resources.upside_down_oral
import sexadventure.composeapp.generated.resources.wheelbarrow
import sexadventure.composeapp.generated.resources.wrapped_lotus

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
        "reverse_missionary" to Res.drawable.reverse_missionary,
        "the_jockey" to Res.drawable.the_jockey,
        "standing_from_behind" to Res.drawable.standing_from_behind,
        "the_cradle" to Res.drawable.the_cradle,
        "speed_bump" to Res.drawable.speed_bump,
        "teaspooning" to Res.drawable.teaspooning,
        "pinball_wizard" to Res.drawable.pinball_wizard,
        "the_valedictorian" to Res.drawable.the_valedictorian,
        "the_snake" to Res.drawable.the_snake,
        "oral_side_lying" to Res.drawable.oral_side_lying,
        "upside_down_oral" to Res.drawable.upside_down_oral,
        "the_queening" to Res.drawable.the_queening,
        "kneeling_oral" to Res.drawable.kneeling_oral,
        "the_stairway" to Res.drawable.the_stairway,
        "couch_surfer" to Res.drawable.couch_surfer,
        "ballet_dancer" to Res.drawable.ballet_dancer,
        "shoulder_holder" to Res.drawable.shoulder_holder,
        "the_g_whiz" to Res.drawable.the_g_whiz,
        "lazy_lady" to Res.drawable.lazy_lady,
        "suspended_congress" to Res.drawable.suspended_congress,
        "the_slip" to Res.drawable.the_slip,
        "the_caboose" to Res.drawable.the_caboose,
        "heir_to_the_throne" to Res.drawable.heir_to_the_throne,
        "the_splitter" to Res.drawable.the_splitter,
        "the_helicopter" to Res.drawable.the_helicopter,
        "the_deck_chair" to Res.drawable.the_deck_chair,
        "wrapped_lotus" to Res.drawable.wrapped_lotus,
        "leg_glider" to Res.drawable.leg_glider,
        "bodyguard" to Res.drawable.bodyguard,
        "lazy_sunday" to Res.drawable.lazy_sunday,
        "the_victory" to Res.drawable.the_victory,
        "the_backbend" to Res.drawable.the_backbend,
        "sofa_rider" to Res.drawable.sofa_rider,
        "the_cross" to Res.drawable.the_cross,
    )

/**
 * Resolves a pose image name to a [DrawableResource].
 * Returns `null` if the name is blank or not found → ListItem shows placeholder.
 */
fun resolveImage(imageName: String?): DrawableResource? {
    if (imageName.isNullOrBlank()) return null
    return imageMap[imageName]
}
