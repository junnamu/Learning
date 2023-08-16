// MainActivity

package com.example.nav_test

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
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
                    modifier = Modifier.fillMaxSize(),
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

    Button(
        onClick = {
            context.startActivity(intent)
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Go to Second Activity")
    }
}

-------------------------------------------
// SecondActivity

package com.example.nav_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


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
    Text(text = "hi")
}


