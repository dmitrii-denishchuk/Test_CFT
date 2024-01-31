package ru.iji.test_cft.data.datasource.remote

import ru.iji.test_cft.data.dto.RandomUserDto

interface RemoteDataSource {

    suspend fun getRandomUsers(): List<RandomUserDto>
}