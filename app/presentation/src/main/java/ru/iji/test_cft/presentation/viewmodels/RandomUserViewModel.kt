package ru.iji.test_cft.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.iji.test_cft.domain.models.RandomUserModel
import ru.iji.test_cft.domain.usecases.GetRandomUsers
import ru.iji.test_cft.presentation.R
import ru.iji.test_cft.presentation.viewmodels.RandomUserViewModel.RandomUsersState.Error
import ru.iji.test_cft.presentation.viewmodels.RandomUserViewModel.RandomUsersState.Loading
import ru.iji.test_cft.presentation.viewmodels.RandomUserViewModel.RandomUsersState.Success
import javax.inject.Inject

@HiltViewModel
class RandomUserViewModel @Inject constructor(
    private val getRandomUsers: GetRandomUsers
) : ViewModel() {

    private val _currentRandomUser = MutableStateFlow<RandomUserModel?>(null)
    val currentRandomUser = _currentRandomUser.asStateFlow()

    private val _randomUsersState = MutableStateFlow<RandomUsersState>(Loading())
    val randomUsersState = _randomUsersState.asStateFlow()

    init {
        requestRandomUsers()
    }

    fun requestRandomUsers(isRefresh: Boolean = false) {
        viewModelScope.launch {
            runCatching { getRandomUsers(isRefresh) }
                .onSuccess {
                    _randomUsersState.value = Success(
                        randomUsers = it,
                        message = "${R.string.loaded_locally}"
                    )
                }
                .onFailure {
                    _randomUsersState.value = Error(
                        message = "${R.string.error_internet_connection}"
                    )
                }
        }
    }

    fun clickedOnRandomUser(randomUser: RandomUserModel? = null) {
        viewModelScope.launch {
            _currentRandomUser.value = randomUser
        }
    }

    fun clearMessage() {
        _randomUsersState.value.randomUsers?.let {
            Success(
                randomUsers = it,
                message = null
            )
        }?.let {
            _randomUsersState.value = it
        }
    }

    sealed class RandomUsersState(
        val isLoading: Boolean,
        val randomUsers: List<RandomUserModel>?,
        val message: String?
    ) {

        class Loading : RandomUsersState(
            isLoading = true,
            randomUsers = null,
            message = null
        )

        class Success(
            randomUsers: List<RandomUserModel>,
            message: String? = null
        ) : RandomUsersState(
            isLoading = false,
            randomUsers = randomUsers,
            message = message
        )

        class Error(message: String?) : RandomUsersState(
            isLoading = false,
            randomUsers = null,
            message = message
        )
    }
}