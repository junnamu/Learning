//질문 체크 기능 구현 테스트
//양자택일 구현

package com.example.project_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project_test.ui.theme.Project_testTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Project_testTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    QuestionPage()
                }
            }
        }
    }
}

data class Answer(val text: String, var isSelected: Boolean = false)

@Composable
fun QuestionPage() {
    val question = "What is your favorite color?"
    val answerOptions = remember {
        listOf(
            Answer("Blue"),
            Answer("Red")
        )
    }
    val selectedAnswer = remember {
        mutableStateOf<Answer?>(null)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = question,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        answerOptions.forEachIndexed { index, answer ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {
                        selectedAnswer.value = answer
                        answerOptions.forEach { it.isSelected = (it == answer) }
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = answer.isSelected,
                    onClick = {
                        selectedAnswer.value = answer
                        answerOptions.forEach { it.isSelected = (it == answer) }
                    },
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(text = answer.text)
            }
        }

        if (selectedAnswer.value != null) {
            // null일경우에 대한 작동 영역
        }
    }
}
