package cl.gersard.covidexercise

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        finishSplash()
    }

    private fun finishSplash() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}