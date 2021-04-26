package cl.gersard.covidexercise.ui.cases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.gersard.covidexercise.R
import cl.gersard.covidexercise.core.GenericState
import cl.gersard.covidexercise.domain.CovidResults
import cl.gersard.covidexercise.domain.CovidUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CovidCasesViewModel @Inject constructor(private val useCase: CovidUseCase) : ViewModel() {

    private val _covidResultsState = MutableLiveData<GenericState<CovidResults>>()
    val covidResultsState: LiveData<GenericState<CovidResults>> get() = _covidResultsState

    private val _currentDateSelected = MutableLiveData<Long>()
    val currentDateSelected: LiveData<Long> get() = _currentDateSelected

    init {
        fetchCovidCases()
    }

    fun updateDate(dateTimestamp: Long){
        _currentDateSelected.value = dateTimestamp
    }

    fun fetchCovidCases(date: String? = null) {
        viewModelScope.launch {
            _covidResultsState.value = GenericState.Loading(true)
            try {
                val results = useCase.getCases(date)
                results?.let {
                    _covidResultsState.value = GenericState.Success(it)
                } ?: kotlin.run {
                    _covidResultsState.value = GenericState.Error(R.string.unexpected_error)
                }
            } catch (e: Exception) {
                _covidResultsState.value = GenericState.Error(R.string.unexpected_error)
            } finally {
                _covidResultsState.value = GenericState.Loading(false)
            }

        }
    }


}