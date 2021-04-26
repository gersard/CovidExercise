package cl.gersard.covidexercise.domain

import cl.gersard.covidexercise.data.repository.CovidRepository
import javax.inject.Inject

class CovidUseCaseImpl @Inject constructor(private val covidRepository: CovidRepository) :
    CovidUseCase {

    override suspend fun getCases(date: String?): CovidResults? {
        if (date == null) return null

        return covidRepository.getCovidCases(date)
    }
}