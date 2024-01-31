package ru.iji.test_cft.app.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.iji.test_cft.data.database.local.Database
import ru.iji.test_cft.data.database.local.LocalDatabase
import ru.iji.test_cft.data.database.local.LocalDatabaseDao
import ru.iji.test_cft.data.database.remote.RandomUserApi
import ru.iji.test_cft.data.datasource.local.LocalDataSource
import ru.iji.test_cft.data.datasource.local.LocalDataSourceImpl
import ru.iji.test_cft.data.datasource.remote.RemoteDataSource
import ru.iji.test_cft.data.datasource.remote.RemoteDataSourceImpl
import ru.iji.test_cft.data.repository.RandomUserRepositoryImpl
import ru.iji.test_cft.data.utils.Converter
import ru.iji.test_cft.domain.repository.RandomUserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    private const val BASE_URL = "https://randomuser.me/api/"
    private const val LOCAL_DB = "local.db"

    @Provides
    @Singleton
    fun provideRandomUserRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): RandomUserRepository {
        return RandomUserRepositoryImpl(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource
        )
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(service: RandomUserApi): RemoteDataSource {
        return RemoteDataSourceImpl(service = service)
    }

    @Provides
    @Singleton
    fun provideRandomUserApi(moshi: Moshi): RandomUserApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(RandomUserApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(localDatabase: LocalDatabase): LocalDataSource {
        return LocalDataSourceImpl(localDatabase)
    }

    @Provides
    @Singleton
    fun provideLocalDatabase(localDatabaseDao: LocalDatabaseDao): LocalDatabase {
        return LocalDatabase(localDatabaseDao)
    }

    @Provides
    @Singleton
    fun provideLocalDatabaseDao(database: Database): LocalDatabaseDao {
        return database.localDatabaseDao
    }

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context,
        moshi: Moshi
    ): Database {
        return Room
            .databaseBuilder(
                context,
                Database::class.java,
                LOCAL_DB
            )
            .addTypeConverter(Converter(moshi))
            .build()
    }
}