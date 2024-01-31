package ru.iji.test_cft.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.iji.test_cft.domain.repository.RandomUserRepository
import ru.iji.test_cft.domain.usecases.GetRandomUsers

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideRandomUsers(randomUserRepository: RandomUserRepository): GetRandomUsers {
        return GetRandomUsers(randomUserRepository = randomUserRepository)
    }
}