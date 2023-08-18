package com.example.nav_test

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext


class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SecondScreenContent()
        }
    }
}

@Composable
fun SecondScreenContent() {
    View()
}

@Composable
fun View() {
    val context = LocalContext.current
    val intent = Intent(context, MainActivity::class.java)
    Column {
        Text(text = "두번째 화면")

        Button(
            onClick = {
                context.startActivity(intent)
            }, modifier = Modifier.fillMaxWidth()
        ) {
            Text("메인화면 이동")
        }
    }
}