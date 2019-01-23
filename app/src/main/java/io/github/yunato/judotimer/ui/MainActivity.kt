package io.github.yunato.judotimer.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import io.github.yunato.judotimer.R

class MainActivity : AppCompatActivity() {
    val TAG: String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton = findViewById(R.id.start_button) as Button

        startButton.setOnClickListener {
            Log.d(TAG, "START")
        }
    }
}