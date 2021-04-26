package cl.gersard.covidexercise.core

import androidx.annotation.StringRes


sealed class GenericState {

    data class Loading(val isLoading: Boolean) : GenericState()
    data class Error(@StringRes val errorMessage: Int) : GenericState()
    data class Success<T>(var data: T) : GenericState()


}