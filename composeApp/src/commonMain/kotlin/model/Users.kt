package model

import classes.Utils.BabyBlueHex
import classes.Utils.LightGreenHex
import classes.Utils.RedOrangeHex
import classes.Utils.RedPinkHex
import classes.Utils.VioletHex


data class Users(
    var id: Long? = null,
    var name: String? = "",
    var email: String? = "",
    var password: String? = "",
    var address: String? = ""
) {
    companion object {
        private val colors = listOf(
            RedOrangeHex, RedPinkHex, LightGreenHex, BabyBlueHex, VioletHex
        )

        fun generateRandomColor() = colors.random()
    }
}

