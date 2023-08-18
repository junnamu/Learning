package com.example.nav_test

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.nav_test.ui.theme.Nav_testTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Nav_testTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    ButtonToSecondActivity()
                }
            }
        }
    }
}

@Composable
fun ButtonToSecondActivity() {
    val context = LocalContext.current
    val intent = Intent(context, SecondActivity::class.java)
    Column {
        Text(text = "메인화면")

        Button(
            onClick = {
                context.startActivity(intent)
            }, modifier = Modifier.fillMaxWidth()
        ) {
            Text("두번째 화면 이동")
        }
    }
}



