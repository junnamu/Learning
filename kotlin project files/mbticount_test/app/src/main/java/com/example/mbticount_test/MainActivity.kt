package com.example.mbticount_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class MbtiType { I, E }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                MbtiQuestionPage()
            }
        }
    }
}

data class Answer(val text: String, val mbtiType: MbtiType)

@Composable
fun MbtiQuestionPage() {
    var currentQuestionIndex by remember { mutableStateOf(0) }
    val questionList = remember {
        listOf(
            "어떤 것이 당신을 더 활성화시킵니까?",
            // 더 많은 질문 추가...
        )
    }
    val answerOptionsList = remember {
        listOf(
            listOf(
                Answer("혼자 시간을 보내는 것", MbtiType.I),
                Answer("사람들과 함께 있는 것", MbtiType.E)
            ),
            // 다른 질문에 대한 더 많은 답변 옵션 추가...
        )
    }

    val selectedAnswers = remember {
        mutableStateListOf<Answer?>()
    }

    val currentQuestion = questionList.getOrNull(currentQuestionIndex)
    val currentAnswerOptions = answerOptionsList.getOrNull(currentQuestionIndex)

    if (currentQuestion != null && currentAnswerOptions != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = currentQuestion,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            currentAnswerOptions.forEach { answer ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(8.dp).clickable {
                            selectedAnswers[currentQuestionIndex] = answer
                        }, verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedAnswers[currentQuestionIndex] == answer, onClick = {
                            selectedAnswers[currentQuestionIndex] = answer
                        }, modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(text = answer.text)
                }
            }

            Button(
                onClick = {
                    if (selectedAnswers[currentQuestionIndex] != null) {
                        if (currentQuestionIndex < questionList.size - 1) {
                            currentQuestionIndex++
                        } else {
                            // 다음 화면으로 이동하려면 navigation 컴포넌트 또는 다른 방법을 사용하십시오.
                            // selectedAnswers를 기반으로 MBTI 유형을 계산합니다.
                        }
                    }
                },
                enabled = selectedAnswers[currentQuestionIndex] != null
            ) {
                Text(text = "다음")
            }
        }
    }
}
