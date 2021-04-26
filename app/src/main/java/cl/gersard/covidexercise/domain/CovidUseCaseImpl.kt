package cl.gersard.covidexercise.domain

import cl.gersard.covidexercise.data.remote.ApiConstants
import cl.gersard.covidexercise.data.repository.CovidRepository
import cl.gersard.covidexercise.ext.format
import java.time.LocalDateTime
import javax.inject.Inject

class CovidUseCaseImpl @Inject constructor(private val covidRepository: CovidRepository) :
    CovidUseCase {

    override suspend fun getCases(date: String?): CovidResults? {
        val dateData = date ?: LocalDateTime.now().minusDays(1).format(ApiConstants.FORMAT_DATE_API)
        return covidRepository.getCovidCases(dateData)
    }
}