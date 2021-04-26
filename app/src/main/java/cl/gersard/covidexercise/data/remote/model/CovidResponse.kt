package cl.gersard.covidexercise.data.remote.model


import com.google.gson.annotations.SerializedName

data class CovidResponse(
    @SerializedName("data")
    val data: CovidData?,
    val error: Int?
)