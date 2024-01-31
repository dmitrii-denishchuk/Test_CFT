package ru.iji.test_cft.domain.usecases

import ru.iji.test_cft.domain.repository.RandomUserRepository
import javax.inject.Inject

class GetRandomUsers @Inject constructor(private val randomUserRepository: RandomUserRepository) {

    suspend operator fun invoke(isRefresh: Boolean) = randomUserRepository.getRandomUsers(isRefresh)
}