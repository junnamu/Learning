package com.example.countup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.countup.ui.theme.CountUpTheme

class MainActivity : AppCompatActivity() {

    private var clickCount = 0
    private lateinit var button: Button
    private lateinit var counterTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)
        counterTextView = findViewById(R.id.counterTextView)

        button.setOnClickListener {
            clickCount++
            counterTextView.text = clickCount.toString()
        }
    }
}



