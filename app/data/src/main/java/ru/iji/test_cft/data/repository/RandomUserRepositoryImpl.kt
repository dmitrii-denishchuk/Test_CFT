package ru.iji.test_cft.data.repository

import ru.iji.test_cft.data.datasource.local.LocalDataSource
import ru.iji.test_cft.data.datasource.remote.RemoteDataSource
import ru.iji.test_cft.data.dto.RandomUserDto
import ru.iji.test_cft.data.mapper.RandomUserMapper.toRandomUserModel
import ru.iji.test_cft.domain.models.RandomUserModel
import ru.iji.test_cft.domain.repository.RandomUserRepository
import javax.inject.Inject

class RandomUserRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : RandomUserRepository {

    override suspend fun getRandomUsers(isRefresh: Boolean): List<RandomUserModel> {
        return when (isRefresh) {
            true -> try {
                remoteDataSource.getRandomUsers()
                    .also { it.saveRandomUsers() }
                    .map { it.toRandomUserModel() }
            } catch (e: Exception) {
                getRandomUsers(isRefresh = false)
            }

            false -> localDataSource.gatSavedRandomUsers()
                .ifEmpty {
                    remoteDataSource.getRandomUsers()
                        .also { it.saveRandomUsers() }
                }
                .map { it.toRandomUserModel() }
        }
    }

    private suspend fun List<RandomUserDto>.saveRandomUsers() {
        localDataSource.saveRandomUsers(this)
    }
}