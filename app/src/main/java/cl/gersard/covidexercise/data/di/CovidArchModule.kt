package cl.gersard.covidexercise.data.di

import cl.gersard.covidexercise.data.remote.datasource.CovidRemoteDataSource
import cl.gersard.covidexercise.data.remote.datasource.CovidRemoteDataSourceImpl
import cl.gersard.covidexercise.data.repository.CovidRepository
import cl.gersard.covidexercise.data.repository.CovidRepositoryImpl
import cl.gersard.covidexercise.domain.CovidUseCase
import cl.gersard.covidexercise.domain.CovidUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class CovidArchModule {

    @Binds
    abstract fun bindsCovidRemoteDataSource(dataSourceImpl: CovidRemoteDataSourceImpl): CovidRemoteDataSource

    @Binds
    abstract fun bindsCovidRepository(repoImpl: CovidRepositoryImpl): CovidRepository

    @Binds
    abstract fun bindsCovidUseCase(useCase: CovidUseCaseImpl): CovidUseCase



}