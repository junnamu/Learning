package com.example.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test.ui.theme.TestTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestTheme {
                Surface(
                    modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.background
                ) {
                    Slack()
                }
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun Slack() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            FixBoxTop()
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                item {
                    TextField(value = remember { TextFieldValue() },
                        onValueChange = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        label = { Text("다음으로 이동...") })
                }

                val menuItems = listOf("스레드", "캔버스", "초안 및 전송됨", "나중에")

                itemsIndexed(menuItems) { index, menuItem ->
                    // 여기서 각 메뉴에 대한 텍스트를 결정합니다.
                    val textToShow = when (index) {
                        0 -> "$menuItem"
                        1 -> "$menuItem"
                        2 -> "$menuItem"
                        3 -> "$menuItem"
                        else -> "$menuItem"
                    }

                    Text(text = textToShow, fontSize = 18.sp)

                }
                item {
                    Divider(color = Color.Black, thickness = 0.8.dp)
                }

                val menuItems2 =
                    listOf("_공지사항", "_질문있어요", "_ai과정", "_app과정", "오프더레코드", "일정알림", "점심후기 - 공유해요")

                itemsIndexed(menuItems2) { index, menuItem ->
                    // 여기서 각 메뉴에 대한 텍스트를 결정합니다.
                    val textToShow = when (index) {
                        0 -> "$menuItem"
                        1 -> "$menuItem"
                        2 -> "$menuItem"
                        3 -> "$menuItem"
                        4 -> "$menuItem"
                        5 -> "$menuItem"
                        6 -> "$menuItem"
                        else -> "$menuItem"
                    }
                    Text(text = textToShow, fontSize = 18.sp)
                }
            }
            FixBoxBottom()
        }
    }

}


@Composable
fun FixBoxTop() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color.White)
    ) {
        Text(
            text = "상단 메뉴", modifier = Modifier
                .padding(16.dp)
                .align(Alignment.Center)
        )
    }
}

@Composable
fun FixBoxBottom(

) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color.White)

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "홈",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)

            )

            Text(
                text = "DM",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)

            )

            Text(
                text = "멘션",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)

            )

            Text(
                text = "검색",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)

            )

            Text(
                text = "나",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)

            )

        }
    }
}

