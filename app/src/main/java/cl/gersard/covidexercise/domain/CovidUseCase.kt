package cl.gersard.covidexercise.domain

interface CovidUseCase {

    suspend fun getCases(date: String? = null): CovidResults?

}