package ru.iji.test_cft.presentation.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Face
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import ru.iji.test_cft.domain.models.RandomUserModel
import ru.iji.test_cft.presentation.R
import ru.iji.test_cft.presentation.components.RandomUserActionButtons
import ru.iji.test_cft.presentation.components.RandomUserActionButtons.CallButton
import ru.iji.test_cft.presentation.components.RandomUserActionButtons.EmailButton
import ru.iji.test_cft.presentation.components.RandomUserActionButtons.MapButton
import ru.iji.test_cft.presentation.viewmodels.RandomUserViewModel

@Composable
fun RandomUserDetailsScreen(randomUserViewModel: RandomUserViewModel) {

    val currentRandomUser by randomUserViewModel.currentRandomUser.collectAsStateWithLifecycle()

    currentRandomUser?.let { user ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.medium_padding)),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(all = dimensionResource(id = R.dimen.medium_padding))
        ) {
            item { RandomUserHead(currentRandomUser = user) }
            item { RandomUserButtons(currentRandomUser = user) }
            item { RandomUserContactInformation(currentRandomUser = user) }
            item { RandomUserPersonalInformation(currentRandomUser = user) }
            item { RandomUserLocationInformation(currentRandomUser = user) }
        }
    }
}

@Composable
fun RandomUserCard(
    cardName: Int,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(modifier = Modifier.padding(all = dimensionResource(id = R.dimen.medium_padding))) {
            Text(
                text = stringResource(id = cardName),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.small_padding))
            )

            content()
        }
    }
}

@Composable
fun RandomUserHead(currentRandomUser: RandomUserModel) {

    @OptIn(ExperimentalGlideComposeApi::class)
    GlideImage(
        model = currentRandomUser.picture?.large,
        contentDescription = stringResource(id = R.string.large_user_picture_description),
        failure = placeholder(rememberVectorPainter(image = Icons.TwoTone.Face)),
        modifier = Modifier
            .size(dimensionResource(id = R.dimen.large_user_picture_size))
            .clip(shape = CircleShape)
    )

    Text(
        text = buildString
        {
            append(
                currentRandomUser.name?.title,
                " ",
                currentRandomUser.name?.first,
                " ",
                currentRandomUser.name?.last
            )
        },
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.displaySmall,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun RandomUserButtons(currentRandomUser: RandomUserModel) {

    val context = LocalContext.current
    val buttons = listOf(
        CallButton,
        EmailButton,
        MapButton
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        buttons.forEach { button ->
            IconButton(
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                onClick = {
                    sendIntent(
                        context = context,
                        button = button,
                        user = currentRandomUser
                    )
                }
            ) {
                Icon(
                    imageVector = button.icon,
                    contentDescription = stringResource(id = button.description)
                )
            }
        }
    }
}

@Composable
fun RandomUserContactInformation(currentRandomUser: RandomUserModel) {
    RandomUserCard(cardName = R.string.contact_information) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = buildString {
                    with(currentRandomUser) {
                        append(
                            stringResource(id = R.string.phone_number),
                            ": ",
                            phone,
                            "\n",
                            stringResource(id = R.string.cell_phone),
                            ": ",
                            cell,
                            "\n",
                            stringResource(id = R.string.email),
                            ": ",
                            email
                        )
                    }
                },
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Composable
fun RandomUserPersonalInformation(currentRandomUser: RandomUserModel) {
    RandomUserCard(cardName = R.string.personal_information) {
        Text(
            text = buildString {
                with(currentRandomUser) {
                    append(
                        stringResource(id = R.string.nationality),
                        ": ",
                        nat,
                        "\n",
                        stringResource(id = R.string.day_of_birth),
                        ": ",
                        dob?.date,
                        "\n",
                        stringResource(id = R.string.age),
                        ": ",
                        dob?.age,
                        "\n",
                        stringResource(id = R.string.registration_date),
                        ": ",
                        registered?.date
                    )
                }
            },
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
fun RandomUserLocationInformation(currentRandomUser: RandomUserModel) {
    RandomUserCard(cardName = R.string.location_information) {
        Text(
            text = buildString {
                with(currentRandomUser) {
                    append(
                        stringResource(id = R.string.country),
                        ": ",
                        location?.country,
                        "\n",
                        stringResource(id = R.string.state),
                        ": ",
                        location?.state,
                        "\n",
                        stringResource(id = R.string.city_upper_case),
                        ": ",
                        location?.city,
                        "\n",
                        stringResource(id = R.string.street_upper_case),
                        ": ",
                        location?.street?.number,
                        " ",
                        location?.street?.name,
                        "\n",
                        stringResource(id = R.string.timezone),
                        ": ",
                        location?.timezone?.offset
                    )
                }
            },
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

private fun sendIntent(
    context: Context,
    button: RandomUserActionButtons,
    user: RandomUserModel
) {
    ContextCompat.startActivity(
        context,
        Intent().apply {
            when (button) {
                is CallButton -> {
                    action = button.action
                    data = Uri.parse(button.uri + user.cell)
                }

                is EmailButton -> {
                    action = button.action
                    data = (Uri.parse(button.uri))
                    putExtra(Intent.EXTRA_EMAIL, arrayOf(user.email))
                    putExtra(Intent.EXTRA_SUBJECT, "Test CFT")
                    putExtra(Intent.EXTRA_TEXT, "Test CFT")
                }

                is MapButton -> {
                    action = button.action
                    data = Uri.parse(
                        button.uri
                                + user.location?.coordinates?.latitude
                                + ", "
                                + user.location?.coordinates?.longitude
                    )
                }
            }
        },
        null
    )
}