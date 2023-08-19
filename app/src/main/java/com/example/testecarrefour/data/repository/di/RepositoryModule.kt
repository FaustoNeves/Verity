package com.example.testecarrefour.data.repository.di

import com.example.testecarrefour.data.api.DataSource
import com.example.testecarrefour.data.repository.GithubRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesUsersRepository(dataSource: DataSource): GithubRepository {
        return GithubRepository(dataSource)
    }
}