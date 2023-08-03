@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.textfield

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Room
import com.example.textfield.data.AppDatabase
import com.example.textfield.data.MIGRATION_1_2
import com.example.textfield.data.User
import com.example.textfield.ui.theme.TextfieldTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TextfieldTheme {
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
fun Info()  {
    val context = LocalContext.current
    val db = remember {
        Room.databaseBuilder(
            context, AppDatabase::class.java, "contact.db"
        ).addMigrations(MIGRATION_1_2).build()
    }
    val scope = rememberCoroutineScope()
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf(0) }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var enteredName by remember { mutableStateOf("") }
    var enteredAge by remember { mutableStateOf(0) }
    var enteredPhone by remember { mutableStateOf("") }
    var enteredEmail by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "이름",
                fontSize = 30.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(Modifier.height(10.dp))

            TextField(
                value = name,
                onValueChange = { newName -> name = newName },
                label = { Text("사용자의 이름을 입력해주세요") },
                visualTransformation = VisualTransformation.None,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(Modifier.height(20.dp))

            Text(
                text = "나이",
                fontSize = 30.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(Modifier.height(10.dp))

            TextField(
                value = age.toString(),
                onValueChange = { newAgeString ->
                    age = newAgeString.toIntOrNull() ?: 0},
                label = { Text("사용자의 나이를 입력해주세요") },
                visualTransformation = VisualTransformation.None,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(Modifier.height(20.dp))

            Text(
                text = "전화번호",
                fontSize = 30.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
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
                text = "이메일",
                fontSize = 30.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
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

            Spacer(Modifier.height(20.dp))

            Button(
                onClick = {
                    val newUser = User(
                        uid = 0, name = name,  age = age, phone = phone, email = email
                    )

                    scope.launch(Dispatchers.IO) {
                        db.userDao().insertAll(newUser)
                    }
                    enteredName = name
                    enteredAge = age
                    enteredPhone = phone
                    enteredEmail = email
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(width = 130.dp, height = 50.dp)
            ) {
                Text(text = "등록", fontSize = 18.sp)
            }
            Spacer(Modifier.height(15.dp))


            Text(
                text = if (enteredName.isNotBlank()) "등록된 사용자 이름 : $enteredName"
                else "",
                fontSize = 25.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(Modifier.height(10.dp))
            Text(
                text = if (enteredAge !=0) "등록된 나이 : $enteredAge"
                else "",
                fontSize = 25.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(Modifier.height(10.dp))
            Text(
                text = if (enteredPhone.isNotBlank()) "등록된 전화번호 : $enteredPhone"
                else "",
                fontSize = 25.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(Modifier.height(10.dp))
            Text(
                text = if (enteredEmail.isNotBlank()) "등록된 이메일 주소 : $enteredEmail"
                else "",
                fontSize = 25.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}
