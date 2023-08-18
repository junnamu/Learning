package com.example.countup2

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.countup2.ui.theme.Countup2Theme
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Countup2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    MyButton()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable


fun MyButton() {

    }

    fun getTime(formatString: String): String {
        val currentTimeMillis = System.currentTimeMillis()
        val format = SimpleDateFormat(formatString, Locale.getDefault())
        return format.format(currentTimeMillis)
    }


    var button1Time by remember { mutableStateOf("") }
    var button2Time by remember { mutableStateOf("") }
    var button3Time by remember { mutableStateOf("") }
    var button4Time by remember { mutableStateOf("") }
    var button5Time by remember { mutableStateOf("") }



    Column {
        Button(
            onClick = {
                count++
                sharedPref?.edit()?.putInt("counter", count)?.apply()
                button1Time = getTime("hh시mm분ss초")
            },
            colors = ButtonDefaults.buttonColors(Color.Black),
            modifier = Modifier.size(width = 100.dp, height = 100.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$count",
                    fontSize = 35.sp,
                    color = Color.White,
                    fontFamily = androidx.compose.ui.text.font.FontFamily.Cursive
                )
            }
        }

        Button(
            onClick = {
                count = 0
                sharedPref?.edit()?.putInt("counter", 0)?.apply()
                button2Time = getTime("hh시mm분ss초")
            },
            colors = ButtonDefaults.buttonColors(Color.Black),
            modifier = Modifier.size(width = 150.dp, height = 100.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Reset",
                    fontSize = 35.sp,
                    color = Color.White,
                    fontFamily = androidx.compose.ui.text.font.FontFamily.Cursive
                )
            }
        }


        Button(
            onClick = {
                count--
                sharedPref?.edit()?.putInt("counter", count)?.apply()
                button3Time = getTime("hh시mm분ss초")
            },
            colors = ButtonDefaults.buttonColors(Color.Black),
            modifier = Modifier.size(width = 100.dp, height = 100.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "-1",
                    fontSize = 35.sp,
                    color = Color.White,
                    fontFamily = androidx.compose.ui.text.font.FontFamily.Cursive
                )
            }
        }

        Button(
            onClick = {
                count *= 2
                sharedPref?.edit()?.putInt("counter", count)?.apply()
                button4Time = getTime("hh시mm분ss초")
            },
            colors = ButtonDefaults.buttonColors(Color.Black),
            modifier = Modifier.size(width = 150.dp, height = 100.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "x2",
                    fontSize = 65.sp,
                    color = Color.White,
                    fontFamily = androidx.compose.ui.text.font.FontFamily.Cursive
                )
            }
        }

        Button(
            onClick = {
                count /= 2
                sharedPref?.edit()?.putInt("counter", count)?.apply()
                button4Time = getTime("hh시mm분ss초")
            },
            colors = ButtonDefaults.buttonColors(Color.Black),
            modifier = Modifier.size(width = 150.dp, height = 100.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "1/2",
                    fontSize = 35.sp,
                    color = Color.White,
                    fontFamily = androidx.compose.ui.text.font.FontFamily.Cursive
                )
            }
        }

        Button(
            onClick = {
                sharedPref?.edit()?.putInt("counter", count)?.apply()
            },
            colors = ButtonDefaults.buttonColors(Color.Black),
            modifier = Modifier.size(width = 400.dp, height = 180.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "Button 1 Time: $button1Time\n" + "Button 2 Time: $button2Time\n" + "Button 3 Time: $button3Time\n" + "Button 4 Time: $button4Time\n" + "Button 5 Time: $button5Time",
                    fontSize = 15.sp,
                    color = Color.White,
                    fontFamily = FontFamily.Cursive
                )
            }
        }
    }
}