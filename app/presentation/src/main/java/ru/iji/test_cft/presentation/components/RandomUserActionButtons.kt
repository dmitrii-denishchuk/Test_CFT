package ru.iji.test_cft.presentation.components

import android.content.Intent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.ui.graphics.vector.ImageVector
import ru.iji.test_cft.presentation.R

const val CALL = "tel:"
const val EMAIL = "mailto:"
const val MAP = "https://maps.google.com/maps/search/"

sealed class RandomUserActionButtons(
    val icon: ImageVector,
    val description: Int,
    val action: String,
    val uri: String
) {
    data object CallButton : RandomUserActionButtons(
        icon = Icons.Rounded.Call,
        description = R.string.call_description,
        action = Intent.ACTION_DIAL,
        uri = CALL
    )

    data object EmailButton : RandomUserActionButtons(
        icon = Icons.Rounded.Email,
        description = R.string.send_email_description,
        action = Intent.ACTION_SEND,
        uri = EMAIL
    )

    data object MapButton : RandomUserActionButtons(
        icon = Icons.Rounded.LocationOn,
        description = R.string.see_coordinates_description,
        action = Intent.ACTION_VIEW,
        uri = MAP
    )
}