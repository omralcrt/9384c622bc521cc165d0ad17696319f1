package com.omralcorut.spacedelivery.enums

import com.omralcorut.spacedelivery.R

enum class GameOverType(
    val description: Int
) {
    SPACESUIT_OVER(
        R.string.stations_game_over_dialog_spacesuit_over_message
    ),
    EUS_OVER(
        R.string.stations_game_over_dialog_eus_over_message
    ),
    DAMAGE_CAPACITY_OVER(
        R.string.stations_game_over_dialog_damage_capacity_over_message
    ),
    GAME_OVER(
        R.string.stations_game_over_dialog_game_over_message
    ),
    NOT_OVER(
        0
    )
}