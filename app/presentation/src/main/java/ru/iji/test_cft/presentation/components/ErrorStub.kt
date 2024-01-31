package ru.iji.test_cft.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.twotone.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import ru.iji.test_cft.presentation.R

@Composable
fun ErrorStub(error: String) {
    Column(
        modifier = Modifier
            .padding(all = dimensionResource(id = R.dimen.medium_padding))
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Rounded.Close,
            contentDescription = stringResource(id = R.string.loading_error_description),
            tint = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.size(dimensionResource(id = R.dimen.large_user_picture_size))
        )

        Text(
            text = error,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium
        )
    }
}