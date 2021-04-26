package cl.gersard.covidexercise.ui.cases

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import cl.gersard.covidexercise.R
import cl.gersard.covidexercise.core.GenericState
import cl.gersard.covidexercise.core.GlobalConstants
import cl.gersard.covidexercise.data.remote.ApiConstants
import cl.gersard.covidexercise.databinding.ActivityMainBinding
import cl.gersard.covidexercise.domain.CovidResults
import cl.gersard.covidexercise.ext.format
import cl.gersard.covidexercise.ext.gone
import cl.gersard.covidexercise.ext.visible
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.time.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding
    private val viewModel by viewModels<CovidCasesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        observeCovidCases()
        observeDateSelection()

        viewBinding.btnSelectDate.setOnClickListener {
            viewBinding.btnSelectDate.isEnabled = false
            showDatePicker()
        }
    }

    private fun observeCovidCases() {
        viewModel.covidResultsState.observe(this, { state ->
            when (state) {
                is GenericState.Error -> handleError(state.error)
                is GenericState.Loading -> handleLoading(state.loading)
                is GenericState.Success -> setDataOnUi(state.data)
            }
        })
    }

    private fun observeDateSelection() {
        viewModel.currentDateSelected.observe(this, {
            val offsetDateTime =
                OffsetDateTime.ofInstant(Instant.ofEpochMilli(it), ZoneId.systemDefault())
                    .withOffsetSameInstant(ZoneOffset.UTC)
            viewModel.fetchCovidCases(offsetDateTime.format(ApiConstants.FORMAT_DATE_API))
        })
    }

    private fun showDatePicker() {
        val datePicker =
            MaterialDatePicker.Builder.datePicker().apply {
                setTitleText(getString(R.string.date_picker_title))
                setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                setCalendarConstraints(
                    getCalendarConstraints()
                )
            }.build()

        datePicker.show(supportFragmentManager, "tag")
        datePicker.addOnPositiveButtonClickListener {
            viewModel.updateDate(it)
        }

        datePicker.addOnDismissListener { viewBinding.btnSelectDate.isEnabled = true }
    }

    private fun getCalendarConstraints(): CalendarConstraints {
        return CalendarConstraints.Builder()
            .setValidator(
                DateValidatorPointBackward.before(
                    LocalDateTime.now().minusDays(1).toInstant(ZoneOffset.UTC)
                        .toEpochMilli()
                )
            )
            .build()
    }


    private fun handleError(@StringRes error: Int) {
        showError(error)
        setEmptyData()
    }

    private fun showError(@StringRes error: Int) {
        Snackbar.make(viewBinding.root, getString(error), Snackbar.LENGTH_SHORT)
            .show()
    }

    private fun setEmptyData() {
        viewBinding.tvCurrentDateSelected.text = getString(R.string.no_data)
        viewBinding.tvTotalConfirmedCases.text = getString(R.string.no_data)
        viewBinding.tvTotalDeathCases.text = getString(R.string.no_data)
    }

    private fun handleLoading(loading: Boolean) {
        if (loading) {
            viewBinding.pbLoading.visible(true)
            hideDataViews()
        } else {
            viewBinding.pbLoading.gone(true)
            showDataViews()
        }
    }

    private fun showDataViews() {
        viewBinding.tvCurrentDateSelected.visible()
        viewBinding.tvTotalConfirmedCases.visible()
        viewBinding.tvTotalDeathCases.visible()
        viewBinding.imageView.visible()
        viewBinding.btnSelectDate.visible()
    }

    private fun hideDataViews() {
        viewBinding.tvCurrentDateSelected.gone()
        viewBinding.tvTotalConfirmedCases.gone()
        viewBinding.tvTotalDeathCases.gone()
        viewBinding.imageView.gone()
        viewBinding.btnSelectDate.gone()
    }

    private fun setDataOnUi(data: CovidResults) {
        with(viewBinding) {
            tvCurrentDateSelected.text = data.date
            tvTotalConfirmedCases.text =
                getString(R.string.confirmed_cases, data.confirmedCases.toString())
            tvTotalDeathCases.text = getString(R.string.death_cases, data.deathCases.toString())
        }
    }


}