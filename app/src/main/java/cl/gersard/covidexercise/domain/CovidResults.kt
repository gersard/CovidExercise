package cl.gersard.covidexercise.domain

data class CovidResults(
    val date: String,
    val confirmedCases: Int,
    val deathCases: Int
)