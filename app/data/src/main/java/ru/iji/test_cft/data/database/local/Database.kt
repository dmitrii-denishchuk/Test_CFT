package ru.iji.test_cft.data.database.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.iji.test_cft.data.dto.RandomUserDto
import ru.iji.test_cft.data.utils.Converter

@Database(
    entities = [RandomUserDto::class],
    exportSchema = false,
    version = 1
)
@TypeConverters(Converter::class)
abstract class Database : RoomDatabase() {
    abstract val localDatabaseDao: LocalDatabaseDao
}