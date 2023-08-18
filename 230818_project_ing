//맵-인덱스 키값 저장 방식으로 버튼 동작 수정 필요

package com.example.comp_nav_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.example.comp_nav_test.ui.theme.Comp_nav_testTheme
import kotlinx.coroutines.delay


class ScoreViewModel : ViewModel() {
    private val answerMap = mutableMapOf<String, Answer>()
    private val scoreMap = mutableMapOf<String, Int>()

    fun saveAnswer(questionId: String, answer: Answer) {
        answerMap[questionId] = answer
    }

    fun getAnswer(questionId: String): Answer? {
        return answerMap[questionId]
    }

    fun saveScore(questionId: String, score: Int) {
        scoreMap[questionId] = score
    }

    fun getScore(questionId: String): Int {
        return scoreMap[questionId] ?: 0
    }

    fun clearQuestionData(questionId: String) {
        answerMap.remove(questionId)
        scoreMap.remove(questionId)
    }
}

data class Answer(val text: String, var isSelected: Boolean = false)

@Composable
fun AppNavigation(scoreViewModel: ScoreViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController, startDestination = "q1"
    ) {
        composable("title") { Title(navController) }
        composable("q1") { Q1(navController, scoreViewModel) }
        composable("q2") { Q2(navController, scoreViewModel) }
        composable("q3") { Q3(navController, scoreViewModel) }
        composable("q4") { Q4(navController, scoreViewModel) }
    }
}



class MainActivity : ComponentActivity() {
    private lateinit var scoreViewModel: ScoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scoreViewModel = ViewModelProvider(this).get(ScoreViewModel::class.java)
        setContent {
            Comp_nav_testTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(scoreViewModel)
                }
            }
        }
    }
}

@Composable
fun Title(navController: NavController) {
    val isVisible = remember { mutableStateOf(true) }

    LaunchedEffect(isVisible) {
        while (true) {
            delay(600)
            isVisible.value = !isVisible.value
        }
    }

    val customFont = FontFamily(
        Font(R.font.cafe24_regular, FontWeight.Normal), Font(R.font.cafe24_bold, FontWeight.Bold)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                isVisible.value = !isVisible.value
                navController.navigate("q1")
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "MBTI 유형 검사",
            fontSize = 50.sp,
            fontFamily = customFont,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(1.dp))
        Text(
            text = "&",
            fontSize = 50.sp,
            fontFamily = customFont,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(1.dp))
        Text(
            text = "유형별 재테크 추천",
            fontSize = 50.sp,
            fontFamily = customFont,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        AnimatedVisibility(
            visible = isVisible.value, enter = fadeIn() + expandIn(), exit = shrinkOut()
        ) {
            Text(
                text = "Click",
                fontSize = 35.sp,
                color = Color.DarkGray,
                modifier = Modifier
                    .alpha(0.5f)
                    .animateContentSize(),
                fontWeight = FontWeight.Bold,
                fontFamily = customFont
            )
        }
    }
}

private fun calculateScore(selectedAnswer: Answer?, answerOptions: List<Answer>): Int {
    return when (selectedAnswer) {
        answerOptions[0] -> 20
        answerOptions[1] -> 20
        else -> 0
    }
}

@Composable
fun QuestionPageContent(
    questionId: String,
    question: String,
    answerOptions: List<Answer>,
    scoreViewModel: ScoreViewModel,
    navController: NavController,
    onNextClicked: () -> Unit,
    onPreviousClicked: () -> Unit,
) {
    val selectedAnswer = remember { mutableStateOf(scoreViewModel.getAnswer(questionId)) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = question,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        answerOptions.forEach { answer ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {
                        selectedAnswer.value = answer
                        scoreViewModel.saveAnswer(questionId, answer)
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(selected = answer.isSelected, onClick = {
                    selectedAnswer.value = answer
                    scoreViewModel.saveAnswer(questionId, answer)
                })
                Text(
                    text = answer.text,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, end = 16.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                selectedAnswer.value = null
                scoreViewModel.clearQuestionData(questionId)
                onPreviousClicked()
            }) {
                Text(text = "이전")
            }

            Button(
                onClick = {
                    selectedAnswer.value?.let { answer ->
                        val score = when (answer) {
                            answerOptions[0] -> 20

                            answerOptions[1] -> 20
                            else -> 0
                        }
                        scoreViewModel.saveScore(questionId, score)
                        onNextClicked()
                    }
                },
                enabled = selectedAnswer.value != null
            ) {
                Text(text = "다음")
            }
        }
    }
}

@Composable
fun Q1(navController: NavController, scoreViewModel: ScoreViewModel) {
    val questionId = "q1" // 고유한 질문 ID

    val answerOptions = remember {
        listOf(
            Answer("Blue"),
            Answer("Red")
        )
    }

    val questionText = "What is your favorite color?"

    val selectedAnswer = scoreViewModel.getAnswer(questionId)


    QuestionPageContent(
        questionId = questionId,
        question = questionText,
        answerOptions = answerOptions,
        scoreViewModel = scoreViewModel,
        navController = navController,
        onNextClicked = {
            scoreViewModel.saveAnswer(questionId, selectedAnswer) // 선택한 답변 저장
            scoreViewModel.saveScore(questionId, calculateScore(selectedAnswer, answerOptions)) // 점수 계산 및 저장
            navController.navigate("q2")
        },
        onPreviousClicked = {
            scoreViewModel.clearQuestionData(questionId) // 선택한 답변과 점수 초기화
            scoreViewModel.restorePreviousScore() // 이전 점수 복구
            navController.popBackStack()
        }
    )
}


@Composable
fun Q2(navController: NavController, scoreViewModel: ScoreViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), verticalArrangement = Arrangement.Center
    ) {

        val answerOptions = remember {
            listOf(
                Answer("답변1"), Answer("답변2")
            )
        }
        val selectedAnswer = remember { mutableStateOf<Answer?>(null) }
        val questionText = "이게 질문이야2?"

        Text(text = "질문2")
        QuestionPageContent(questionText,
            answerOptions,
            selectedAnswer,
            scoreViewModel,
            onNextClicked = {
                scoreViewModel.savePreviousScore()
                navController.navigate("q3") },
            onPreviousClicked = {
                scoreViewModel.restorePreviousScore()
                navController.popBackStack()
            })
    }
}


@Composable
fun Q3(navController: NavController, scoreViewModel: ScoreViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), verticalArrangement = Arrangement.Center
    ) {

        val answerOptions = remember {
            listOf(
                Answer("답변1"), Answer("답변2")
            )
        }
        val selectedAnswer = remember { mutableStateOf<Answer?>(null) }
        val questionText = "이게 질문이야3?"

        Text(text = "질문3")
        QuestionPageContent(questionText,
            answerOptions,
            selectedAnswer,
            scoreViewModel,
            onNextClicked = {
                scoreViewModel.savePreviousScore()
                navController.navigate("q4") },
            onPreviousClicked = {
                scoreViewModel.restorePreviousScore()
                navController.popBackStack()
            })
    }
}

@Composable
fun Q4(navController: NavController, scoreViewModel: ScoreViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), verticalArrangement = Arrangement.Center
    ) {

        val answerOptions = remember {
            listOf(
                Answer("답변1"), Answer("답변2")
            )
        }
        val selectedAnswer = remember { mutableStateOf<Answer?>(null) }
        val questionText = "이게 질문이야4?"

        Text(text = "질문4")
        QuestionPageContent(questionText,
            answerOptions,
            selectedAnswer,
            scoreViewModel,
            onNextClicked = {
                navController.navigate("q1") },
            onPreviousClicked = {
                scoreViewModel.restorePreviousScore()
                navController.popBackStack() })
    }
}


