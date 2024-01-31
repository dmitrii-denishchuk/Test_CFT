package ru.iji.test_cft.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import ru.iji.test_cft.presentation.R
import ru.iji.test_cft.presentation.components.ErrorStub
import ru.iji.test_cft.presentation.layouts.RandomUserLayout
import ru.iji.test_cft.presentation.screens.Route.DETAILS_SCREEN
import ru.iji.test_cft.presentation.viewmodels.RandomUserViewModel
import ru.iji.test_cft.presentation.viewmodels.RandomUserViewModel.RandomUsersState

@Composable
fun RandomUserFeedScreen(
    randomUserViewModel: RandomUserViewModel,
    navHostController: NavHostController
) {

    val context = LocalContext.current
    val randomUsersState by randomUserViewModel.randomUsersState.collectAsStateWithLifecycle()
    val isRefreshing by remember { mutableStateOf(false) }

    @OptIn(ExperimentalMaterialApi::class)
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = { randomUserViewModel.requestRandomUsers(true) }
    )

    @OptIn(ExperimentalMaterialApi::class)
    Box(modifier = Modifier.pullRefresh(pullRefreshState)) {

        when (randomUsersState) {
            is RandomUsersState.Loading ->
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))

            is RandomUsersState.Success -> {
                randomUsersState.message?.toInt()?.let {
                    Toast.makeText(
                        context,
                        stringResource(id = it),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                randomUserViewModel.clearMessage()
            }

            is RandomUsersState.Error ->
                randomUsersState.message?.let {
                    ErrorStub(error = stringResource(id = it.toInt()))
                }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.medium_padding)),
            contentPadding = PaddingValues(all = dimensionResource(id = R.dimen.medium_padding))
        ) {
            randomUsersState.randomUsers?.let { randomUsers ->
                items(items = randomUsers) { randomUser ->
                    RandomUserLayout(
                        randomUser = randomUser,
                        clickedOnRandomUser = {
                            randomUserViewModel.clickedOnRandomUser(it)
                            navHostController.navigate(DETAILS_SCREEN.name)
                        }
                    )
                }
            }
        }

        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}