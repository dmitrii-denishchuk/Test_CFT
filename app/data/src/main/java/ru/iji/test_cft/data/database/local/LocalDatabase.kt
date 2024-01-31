package ru.iji.test_cft.data.database.local

import ru.iji.test_cft.data.dto.RandomUserDto
import javax.inject.Inject

class LocalDatabase @Inject constructor(private val localDatabaseDao: LocalDatabaseDao) {

    suspend fun gatSavedRandomUsers(): List<RandomUserDto> {
        return localDatabaseDao.gatSavedRandomUsers()
    }

    suspend fun saveRandomUsers(randomUsers: List<RandomUserDto>) {
        localDatabaseDao.saveRandomUsers(randomUsers)
    }
}