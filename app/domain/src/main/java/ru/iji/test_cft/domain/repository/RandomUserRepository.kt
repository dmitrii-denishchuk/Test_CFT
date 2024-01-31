package ru.iji.test_cft.domain.repository

import ru.iji.test_cft.domain.models.RandomUserModel

interface RandomUserRepository {

    suspend fun getRandomUsers(isRefresh: Boolean): List<RandomUserModel>
}