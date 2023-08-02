// shared preferences 를 활용한 데이터가 저장되는 버튼 실습

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.countup2.ui.theme.Countup2Theme
import java.text.SimpleDateFormat
import java.util.Locale
import androidx.compose.ui.tooling.preview.Preview as Preview1


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Countup2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val sharedPref = getPreferences(Context.MODE_PRIVATE)
                    val initialButton1Time = sharedPref.getString("button1Time", "")
                    val initialButton2Time = sharedPref.getString("button2Time", "")
                    val initialButton3Time = sharedPref.getString("button3Time", "")
                    val initialButton4Time = sharedPref.getString("button4Time", "")
                    val initialButton5Time = sharedPref.getString("button5Time", "")

                    MyButton(
                        initialButton1Time,
                        initialButton2Time,
                        initialButton3Time,
                        initialButton4Time,
                        initialButton5Time
                    )
                }
            }
        }
    }
}


@Composable
fun MyButton(
    initialButton1Time: String?,
    initialButton2Time: String?,
    initialButton3Time: String?,
    initialButton4Time: String?,
    initialButton5Time: String?
) {
    val activity = LocalContext.current as? Activity
    val sharedPref = remember { activity?.getPreferences(Context.MODE_PRIVATE) }
    var count by remember {
        val countValue = sharedPref?.getInt("counter", 0) ?: 0
        mutableStateOf(countValue)
    }

    fun getTime(formatString: String): String {
        val currentTimeMillis = System.currentTimeMillis()
        val format = SimpleDateFormat(formatString, Locale.getDefault())
        return format.format(currentTimeMillis)
    }


    var button1Time by remember { mutableStateOf(initialButton1Time ?: "") }
    var button2Time by remember { mutableStateOf(initialButton2Time ?: "") }
    var button3Time by remember { mutableStateOf(initialButton3Time ?: "") }
    var button4Time by remember { mutableStateOf(initialButton4Time ?: "") }
    var button5Time by remember { mutableStateOf(initialButton5Time ?: "") }



    Column {
        Button(
            onClick = {
                count++
                sharedPref?.edit()?.putInt("counter", count)?.apply()
                button1Time = getTime("hh시mm분ss초")
                sharedPref?.edit()?.putString("button1Time", button1Time)?.apply()
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
                sharedPref?.edit()?.putString("button2Time", button2Time)?.apply()
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
                sharedPref?.edit()?.putString("button3Time", button3Time)?.apply()
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
                sharedPref?.edit()?.putString("button4Time", button4Time)?.apply()
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
                button5Time = getTime("hh시mm분ss초")
                sharedPref?.edit()?.putString("button5Time", button5Time)?.apply()
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
                    text = "Button 1 Time: $button1Time\n" +
                            "Button 2 Time: $button2Time\n" +
                            "Button 3 Time: $button3Time\n" +
                            "Button 4 Time: $button4Time\n" +
                            "Button 5 Time: $button5Time",
                    fontSize = 15.sp,
                    color = Color.White,
                    fontFamily = androidx.compose.ui.text.font.FontFamily.Cursive
                )
            }
        }

    }
}
