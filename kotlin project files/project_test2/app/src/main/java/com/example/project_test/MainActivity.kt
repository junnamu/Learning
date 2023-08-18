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
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.project_test.ui.theme.Project_testTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Project_testTheme {
                val navController = rememberNavController()
                val viewModel: ScoreViewModel = viewModel()

                NavHost(navController, startDestination = "question1Page") {
                    composable("question1Page") {
                        QuestionPage(navController = navController, viewModel = viewModel, question = "질문 1", answerOptions = listOf(
                            Answer("답변 1-1", score = 1),
                            Answer("답변 1-2", score = 2)
                        ))
                    }
                    composable("question2Page") {
                        QuestionPage(navController = navController, viewModel = viewModel, question = "질문 2", answerOptions = listOf(
                            Answer("답변 2-1", score = 1),
                            Answer("답변 2-2", score = 2)
                        ))
                    }
                    composable("question3Page") {
                        QuestionPage(navController = navController, viewModel = viewModel, question = "질문 3", answerOptions = listOf(
                            Answer("답변 3-1", score = 1),
                            Answer("답변 3-2", score = 2)
                        ))
                    }
                    composable("question4Page") {
                        QuestionPage(navController = navController, viewModel = viewModel, question = "질문 4", answerOptions = listOf(
                            Answer("답변 4-1", score = 1),
                            Answer("답변 4-2", score = 2)
                        ))
                    }
                    composable("question5Page") {
                        QuestionPage(navController = navController, viewModel = viewModel, question = "질문 5", answerOptions = listOf(
                            Answer("답변 5-1", score = 1),
                            Answer("답변 5-2", score = 2)
                        ))
                    }
                    composable("resultPage") {
                        ResultPage(navController = navController, viewModel = viewModel)
                    }
                }
            }
        }
    }
}


data class Answer(val text: String, var score: Int, var isSelected: Boolean = false, var selectedAnswerIndex: Int = -1, var previousAnswerIndex: Int = -1)



class ScoreViewModel : ViewModel() {
    private var _iscore: Int = 0
    private var _escore: Int = 0

    val iscore: Int
        get() = _iscore

    val escore: Int
        get() = _escore

    fun updateScores(decreaseIscore: Boolean, decreaseEscore: Boolean) {
        if (decreaseIscore) {
            _iscore--
        }
        if (decreaseEscore) {
            _escore--
        }
    }

    fun increaseIscore() {
        _iscore++
    }

    fun increaseEscore() {
        _escore++
    }



    fun resetScores() {
        _iscore = 0
        _escore = 0
    }
}


@Composable
fun AnswerList(
    answerOptions: List<Answer>,
    selectedAnswer: MutableState<Answer?>,
    viewModel: ScoreViewModel
) {
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
}

@Composable
fun QuestionPage(navController: NavHostController, viewModel: ScoreViewModel, question: String, answerOptions: List<Answer>) {
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

        AnswerList(answerOptions, selectedAnswer, viewModel)

        Text("iscore: ${viewModel.iscore}")
        Text("escore: ${viewModel.escore}")

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    val previousAnswerIndex = selectedAnswer.value?.selectedAnswerIndex

                    if (previousAnswerIndex != null && previousAnswerIndex >= 0 && previousAnswerIndex < answerOptions.size) {
                        val previousAnswer = answerOptions[previousAnswerIndex]
                        viewModel.updateScores(previousAnswer.score == 1, previousAnswer.score == 2)
                    }

                    navController.popBackStack()
                }
            ) {
                Text("Previous")
            }

            if (selectedAnswer.value != null) {
                Button(
                    onClick = {
                        selectedAnswer.value?.let {
                            it.score += 0
                            if (it.score == 1) {
                                viewModel.increaseIscore()
                            } else if (it.score == 2) {
                                viewModel.increaseEscore()
                            }
                        }


                        // Navigate to the next question or result page
                        when (question) {
                            "질문 1" -> navController.navigate("question2Page")
                            "질문 2" -> navController.navigate("question3Page")
                            "질문 3" -> navController.navigate("question4Page")
                            "질문 4" -> navController.navigate("question5Page")
                            else -> navController.navigate("resultPage")
                        }
                    }
                ) {
                    Text("Next")
                }
            }
        }
    }
}




@Composable
fun ResultPage(navController: NavHostController, viewModel: ScoreViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Result Page",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text("iscore: ${viewModel.iscore}")
        Text("escore: ${viewModel.escore}")

        Button(
            onClick = {
                // Reset scores and navigate back to the QuestionPage
                viewModel.resetScores()
                navController.popBackStack("question1Page", inclusive = false)
            }
        ) {
            Text("Previous")
        }
    }
}