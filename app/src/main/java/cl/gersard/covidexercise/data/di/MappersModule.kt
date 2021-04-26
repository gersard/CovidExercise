package cl.gersard.covidexercise.data.di

import cl.gersard.covidexercise.data.repository.CovidResultsMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MappersModule {

    @Provides
    fun providesCovidResultsMApper() = CovidResultsMapper()

}