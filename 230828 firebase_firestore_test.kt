package com.example.firebasetest


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.firebasetest.ui.theme.FirebasetestTheme
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContent {
            FirebasetestTheme {
                var resultText by remember { mutableStateOf("") }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    WriteFirestoreButton(
                        title = "Write to Firestore",
                        onWrite = {
                            onWrite()
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    ReadFirestoreButton(
                        title = "Read to Firestore",
                        onRead = {
                            onRead { name, age ->
                                resultText = "Name: $name, Age: $age"
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = resultText)
                }
            }
        }
    }
}

@Composable
fun WriteFirestoreButton(
    title: String,
    onWrite: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier,
        onClick = {
            onWrite()
        }
    ) {
        Text(title)
    }
}

@Composable
fun ReadFirestoreButton(
    title: String,
    onRead: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier,
        onClick = {
            onRead()
        }
    ) {
        Text(title)
    }
}

fun onWrite() {
    val db = FirebaseFirestore.getInstance()
    val docRef = db.collection("user").document("johndoe")
    docRef.set(
        mapOf(
            "name" to "John Doe",
            "age" to 30
        )
    )
}


fun onRead(onResult: (String?, Long?) -> Unit) {
    val db = FirebaseFirestore.getInstance()
    val docRef = db.collection("user").document("johndoe")
    docRef.get().addOnSuccessListener { document ->
        if (document.exists()) {
            val name = document.getString("name")
            val age = document.getLong("age")
            onResult(name, age)
        } else {
            onResult(null, null)
        }
    }
}
