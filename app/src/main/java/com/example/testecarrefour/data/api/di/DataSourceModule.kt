package com.example.testecarrefour.data.api.di

import com.example.testecarrefour.data.api.DataSource
import com.example.testecarrefour.data.api.DataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindsDataSource(dataSourceImpl: DataSourceImpl): DataSource
}