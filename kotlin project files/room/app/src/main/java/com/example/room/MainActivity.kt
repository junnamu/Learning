@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.room

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.room.ui.theme.RoomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoomTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Info()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Info() {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Text(
        text = "이름", fontSize = 30.sp, modifier = Modifier.align(Alignment.CenterHorizontally)
    )
    TextField(
        value = name,
        onValueChange = { newName -> name = newName },
        label = { Text("사용자의 이름을 입력해주세요") },
        visualTransformation = VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        modifier = Modifier.align(Alignment.CenterHorizontally)
    )
    Text(
        text = "전화번호", fontSize = 30.sp, modifier = Modifier.align(Alignment.CenterHorizontally)
    )
    Spacer(Modifier.height(10.dp))

    TextField(
        value = phone,
        onValueChange = { newPhone -> phone = newPhone },
        label = { Text("사용자의 전화번호를 입력해주세요") },
        visualTransformation = VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier.align(Alignment.CenterHorizontally)
    )
    Spacer(Modifier.height(20.dp))

    Text(
        text = "이메일", fontSize = 30.sp, modifier = Modifier.align(Alignment.CenterHorizontally)
    )
    Spacer(Modifier.height(10.dp))

    TextField(
        value = email,
        onValueChange = { newEmail -> email = newEmail },
        label = { Text("사용자의 이메일을 입력해주세요") },
        visualTransformation = VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        modifier = Modifier.align(Alignment.CenterHorizontally)
    )


}

