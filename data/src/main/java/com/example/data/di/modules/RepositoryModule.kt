package com.example.data.di.modules

import com.example.data.datasource.local.CurrencyLocalDataSource
import com.example.data.datasource.remote.CurrencyRemoteDataSource
import com.example.data.repository.CurrencyRepositoryImpl
import com.example.domain.repository.CurrencyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCurrencyRepository(
        remoteDataSource: CurrencyRemoteDataSource,
        localDataSource: CurrencyLocalDataSource
    ): CurrencyRepository {
        return CurrencyRepositoryImpl(remoteDataSource, localDataSource)
    }
}
