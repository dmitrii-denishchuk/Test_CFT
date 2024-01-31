package ru.iji.test_cft.data.database.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.iji.test_cft.data.dto.RandomUserDto

@Dao
interface LocalDatabaseDao {

    @Query("SELECT * FROM random_users")
    suspend fun gatSavedRandomUsers(): List<RandomUserDto>

    @Query("DELETE FROM random_users")
    suspend fun clearTable()

    @Insert
    suspend fun insertRandomUsers(randomUserDto: List<RandomUserDto>)

    suspend fun saveRandomUsers(randomUserDto: List<RandomUserDto>) {
        clearTable()
        insertRandomUsers(randomUserDto)
    }
}