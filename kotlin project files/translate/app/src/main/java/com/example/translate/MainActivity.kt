package com.example.translate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Transtext()
                }
            }
        }
    }


@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Transtext() {
    var translatedText by remember { mutableStateOf("") }
    var textTranstlated by remember { mutableStateOf("") }


    val koEnTranslator = remember {
        val options = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.KOREAN)
            .setTargetLanguage(TranslateLanguage.ENGLISH)
            .build()
        Translation.getClient(options)
    }
    var enabled by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        val conditions = DownloadConditions.Builder()
            .requireWifi()
            .build()
        koEnTranslator.downloadModelIfNeeded(conditions)
            .addOnSuccessListener {
                enabled = true
            }
    }

    Column {
        Box(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .height(300.dp)
                .shadow(10.dp, shape = MaterialTheme.shapes.large)
                .background(Color.Blue),
        ) {
            Text(text = "번역", fontSize = 18.sp)

            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                value = translatedText,
                onValueChange = { enteredText -> translatedText = enteredText },
                label = { Text("번역할 텍스트를 입력해주세요") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.background(color = Color.Transparent)
                    .fillMaxSize()
            )
        }

        Box(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .height(300.dp)
                .shadow(10.dp, shape = MaterialTheme.shapes.large)
                .background(Color.White),
        ) {
            Text(
                text = textTranstlated.ifBlank { "" },
                fontSize = 25.sp,
                modifier = Modifier.fillMaxSize()
                )

        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(1.dp)
                .fillMaxWidth()
        ) {
            Button(
                onClick = {
//                enteredText = text
                    koEnTranslator.translate(translatedText)
                        .addOnSuccessListener { translatedText ->
                            textTranstlated = translatedText

                        }
                },
                enabled = enabled,
                modifier = Modifier
                    .padding(20.dp)
                    .size(width = 130.dp, height = 80.dp)
            ) {
                Text(text = "번역", fontSize = 18.sp)
            }

            Button(
                onClick = {
//                enteredText = text
                    koEnTranslator.translate(translatedText)
                        .addOnSuccessListener { translatedText ->
                            textTranstlated = translatedText

                        }
                },
                enabled = enabled,
                modifier = Modifier
                    .padding(20.dp)
                    .size(width = 130.dp, height = 80.dp)
            ) {
                Text(text = "번역", fontSize = 18.sp)
            }

        }
    }
}







