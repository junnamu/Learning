// 다양한 기능을 추가해보았고, 기본적인 레이아웃 구성만 직관적으로 배치 디자인하였다.
// 이후 함수로 빼서 코드 다이어트로 추가 실습을 해볼 수 있겠다
// 

package com.example.countup2

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
                    val sharedPref = getPreferences(MODE_PRIVATE)
                    val initialButton1Time = sharedPref.getString("button1Time", "")
                    val initialButton2Time = sharedPref.getString("button2Time", "")
                    val initialButton3Time = sharedPref.getString("button3Time", "")
                    val initialButton4Time = sharedPref.getString("button4Time", "")
                    val initialButton5Time = sharedPref.getString("button5Time", "")

                    val currentButtonTime = sharedPref.getString("currentButtonTime", "")


                    MyButton(
                        initialButton1Time,
                        initialButton2Time,
                        initialButton3Time,
                        initialButton4Time,
                        initialButton5Time,
                        currentButtonTime
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
    initialButton5Time: String?,
    initialCurrentButtonTime: String?
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

    var currentButtonTime by remember { mutableStateOf(initialCurrentButtonTime ?: "") }
    var showCurrentTime by remember { mutableStateOf(true) }



    Text(
        text = "전세계 인구수 증가량",
        textAlign = TextAlign.Center,
        fontSize = 30.sp,
        modifier = Modifier.padding(top = 25.dp),
        fontWeight = FontWeight.Bold
    )




    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = when {
                    count > 0 -> "+$count"
                    count < 0 -> "$count"
                    else -> "0"
                },
                fontSize = if (count.toString().length > 9) 50.sp else 60.sp,
                color = if (count.toString().length > 9) Color.Red else Color.Black
            )
        }

        Spacer(modifier = Modifier.height(2.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    count++
                    sharedPref?.edit()?.putInt("counter", count)?.apply()
                    button1Time = getTime("hh시 mm분 ss초")
                    sharedPref?.edit()?.putString("button1Time", button1Time)?.apply()
                    currentButtonTime = getTime("hh시 mm분 ss초")
                    sharedPref?.edit()?.putString("currentButtonTime", currentButtonTime)?.apply()

                },
                colors = ButtonDefaults.buttonColors(Color.Black),
                modifier = Modifier.size(width = 80.dp, height = 80.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "+1",
                        fontSize = 25.sp,
                        color = Color.White
                    )
                }
            }

            Button(
                onClick = {
                    count--
                    sharedPref?.edit()?.putInt("counter", count)?.apply()
                    button3Time = getTime("hh시 mm분 ss초")
                    sharedPref?.edit()?.putString("button3Time", button3Time)?.apply()
                    currentButtonTime = getTime("hh시 mm분 ss초")
                    sharedPref?.edit()?.putString("currentButtonTime", currentButtonTime)?.apply()
                },
                colors = ButtonDefaults.buttonColors(Color.Black),
                modifier = Modifier.size(width = 80.dp, height = 80.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "-1",
                        fontSize = 25.sp,
                        color = Color.White

                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    count *= 2
                    sharedPref?.edit()?.putInt("counter", count)?.apply()
                    button4Time = getTime("hh시 mm분 ss초")
                    sharedPref?.edit()?.putString("button4Time", button4Time)?.apply()
                    currentButtonTime = getTime("hh시 mm분 ss초")
                    sharedPref?.edit()?.putString("currentButtonTime", currentButtonTime)?.apply()
                },
                colors = ButtonDefaults.buttonColors(Color.Black),
                modifier = Modifier.size(width = 80.dp, height = 80.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "×2",
                        fontSize = 25.sp,
                        color = Color.White
                    )
                }
            }

            Button(
                onClick = {
                    count /= 2
                    sharedPref?.edit()?.putInt("counter", count)?.apply()
                    button5Time = getTime("hh시 mm분 ss초")
                    sharedPref?.edit()?.putString("button5Time", button5Time)?.apply()
                    currentButtonTime = getTime("hh시 mm분 ss초")
                    sharedPref?.edit()?.putString("currentButtonTime", currentButtonTime)?.apply()
                },
                colors = ButtonDefaults.buttonColors(Color.Black),
                modifier = Modifier.size(width = 80.dp, height = 80.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "÷2",
                        fontSize = 25.sp,
                        color = Color.White
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                {
                    count = 0
                    sharedPref?.edit()?.putInt("counter", 0)?.apply()
                    button2Time = getTime("hh시 mm분 ss초")
                    sharedPref?.edit()?.putString("button2Time", button2Time)?.apply()
                    currentButtonTime = getTime("hh시 mm분 ss초")
                    sharedPref?.edit()?.putString("currentButtonTime", currentButtonTime)?.apply()
                },
                colors = ButtonDefaults.buttonColors(Color.Black),
                modifier = Modifier.size(width = 120.dp, height = 80.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "RESET",
                        fontSize = 21.sp,
                        color = Color.White
                    )
                }
            }
        }


        Divider(
            color = Color.DarkGray,
            modifier = Modifier
                .fillMaxWidth()
                .width(1.dp)
                .padding(10.dp)
                .padding(top = 10.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    sharedPref?.edit()?.putString("currentButtonTime", currentButtonTime)?.apply()
                    showCurrentTime = !showCurrentTime
                },
                colors = ButtonDefaults.buttonColors(Color.Black),
                modifier = Modifier
                    .size(width = 460.dp, height = 180.dp)
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (showCurrentTime) {
                            "인구수 최근 조작 시간 : $currentButtonTime"
                        } else {
                            "ㅡ 최근 조작 시간 ㅡ\n" +
                                    "인구수 +1 조작 시간: $button1Time\n" +
                                    "증가량  초기화  시간: $button2Time\n" +
                                    "인구수 -1 조작 시간: $button3Time\n" +
                                    "인구수 ×2 조작 시간: $button4Time\n" +
                                    "인구수 ÷2 조작 시간: $button5Time"
                        },
                        fontSize = 17.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        lineHeight = 25.sp
                    )
                }
            }



            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "인구수 최근 조작 시간 버튼을 클릭하시면,\n " +
                        "각 버튼별 최근 조작 시간을 확인하실 수 있습니다.",
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
