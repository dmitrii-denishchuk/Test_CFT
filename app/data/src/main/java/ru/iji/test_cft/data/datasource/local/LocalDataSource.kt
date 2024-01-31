package ru.iji.test_cft.data.datasource.local

import ru.iji.test_cft.data.dto.RandomUserDto

interface LocalDataSource {

    suspend fun gatSavedRandomUsers(): List<RandomUserDto>

    suspend fun saveRandomUsers(randomUsers: List<RandomUserDto>)
}