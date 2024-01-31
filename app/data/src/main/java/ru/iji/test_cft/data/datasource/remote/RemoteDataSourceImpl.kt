package ru.iji.test_cft.data.datasource.remote

import ru.iji.test_cft.data.dto.RandomUserDto
import ru.iji.test_cft.data.database.remote.RandomUserApi
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val service: RandomUserApi
) : RemoteDataSource {

    override suspend fun getRandomUsers(): List<RandomUserDto> {
        return service.getData().randomUsers
    }
}