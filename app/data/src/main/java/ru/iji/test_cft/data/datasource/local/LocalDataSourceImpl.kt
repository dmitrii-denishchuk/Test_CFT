package ru.iji.test_cft.data.datasource.local

import ru.iji.test_cft.data.database.local.LocalDatabase
import ru.iji.test_cft.data.dto.RandomUserDto
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val localDatabase: LocalDatabase
) : LocalDataSource {

    override suspend fun gatSavedRandomUsers(): List<RandomUserDto> {
        return localDatabase.gatSavedRandomUsers()
    }

    override suspend fun saveRandomUsers(randomUsers: List<RandomUserDto>) {
        localDatabase.saveRandomUsers(randomUsers)
    }
}