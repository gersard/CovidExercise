package cl.gersard.covidexercise.ui.cases

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cl.gersard.covidexercise.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}