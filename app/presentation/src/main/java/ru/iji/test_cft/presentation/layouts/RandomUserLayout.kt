package ru.iji.test_cft.presentation.layouts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import ru.iji.test_cft.domain.models.RandomUserModel
import ru.iji.test_cft.presentation.R

@Composable
fun RandomUserLayout(
    randomUser: RandomUserModel,
    clickedOnRandomUser: (RandomUserModel?) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { clickedOnRandomUser(randomUser) },
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        @OptIn(ExperimentalGlideComposeApi::class)
        GlideImage(
            model = randomUser.picture?.thumbnail,
            contentDescription = stringResource(id = R.string.thumbnail_user_picture_description),
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.thumbnail_user_picture_size))
                .clip(shape = CircleShape)
        )

        Column {
            Text(
                text = buildString {
                    append(
                        randomUser.name?.first,
                        " ",
                        randomUser.name?.last,
                        " | ",
                        randomUser.cell
                    )
                },
                style = MaterialTheme.typography.labelLarge
            )

            Text(
                text = buildString {
                    randomUser.location?.let {
                        with(it) {
                            append(
                                country,
                                ", ",
                                state,
                                ", ",
                                city,
                                stringResource(id = R.string.city_lower_case),
                                ", ",
                                street?.name,
                                stringResource(id = R.string.street_lower_case),
                                ", ",
                                street?.number,
                                ", ",
                                postcode
                            )
                        }
                    }
                },
                style = MaterialTheme.typography.labelSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}