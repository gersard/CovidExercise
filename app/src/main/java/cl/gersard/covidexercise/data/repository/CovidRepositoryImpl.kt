package cl.gersard.covidexercise.data.repository

import cl.gersard.covidexercise.data.remote.datasource.CovidRemoteDataSource
import cl.gersard.covidexercise.domain.CovidResults
import timber.log.Timber
import javax.inject.Inject

class CovidRepositoryImpl @Inject constructor(
    private val remoteDataSource: CovidRemoteDataSource,
    private val covidResultsMapper: CovidResultsMapper
) :
    CovidRepository {

    override suspend fun getCovidCases(date: String): CovidResults? {
        val response = remoteDataSource.getTotalCovidCases(date)
        return if (response.data != null) {
            covidResultsMapper.map(response.data, date)
        } else {
            Timber.e("Error code from network datasource: ${response.error}")
            null
        }
    }
}