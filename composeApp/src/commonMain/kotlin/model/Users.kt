package model

import classes.Utils.BabyBlueHex
import classes.Utils.LightBlue2Hex
import classes.Utils.LightBlueHex
import classes.Utils.LightGreen2Hex
import classes.Utils.LightGreen3Hex
import classes.Utils.LightGreenHex
import classes.Utils.RedOrangeHex
import classes.Utils.RedPink2Hex
import classes.Utils.RedPink3Hex
import classes.Utils.RedPinkHex
import classes.Utils.Violet2Hex
import classes.Utils.Violet3Hex
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
            RedOrangeHex,
            RedPinkHex, LightGreenHex,
            BabyBlueHex, VioletHex, LightBlueHex,
            Violet3Hex, Violet2Hex, LightGreen2Hex, RedPink2Hex,
            RedPink3Hex, LightBlue2Hex, LightGreen3Hex, RedOrangeHex
        )

        fun generateRandomColor() = colors.random()
    }
}

