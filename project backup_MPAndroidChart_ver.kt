//project backup_MPAndroidChart_ver.kt
//MPAndroidChart에서 컴포즈 그리기 그래프로 변경 예정
// 일부 코드 사라졌다 집에서 코드 다시 백업 필요



package com.example.comp_nav_test
            scoreViewModel.clearAnswerScore(answerIndex)
            navController.popBackStack()
        }
    )
}

@Composable
fun Q5(navController: NavController, scoreViewModel: ScoreViewModel) {
    val answerIndex = 5

    val answerOptions = remember {
        listOf(
            Answer("Choice X"),
            Answer("Choice Y")
        )
    }

    val questionText = "Last question"

    QuestionPageContent(
        answerIndex = answerIndex,
        question = questionText,
        answerOptions = answerOptions,
        scoreViewModel = scoreViewModel, // scoreViewModel 전달 추가
        onNextClicked = {
            navController.navigate("results")
        },
        onPreviousClicked = {
            scoreViewModel.clearAnswerScore(answerIndex)
            navController.popBackStack()
        }
    )
}


@Composable
fun ResultsPage(scoreViewModel: ScoreViewModel) {
    val answerScores = scoreViewModel.getAnswerScores()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Text(
            text = "Results",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        val answerChoiceCounts = mutableMapOf<Int, Int>()

        for ((index, score) in answerScores.withIndex()) {
            val selectedAnswerIndex = scoreViewModel.getSelectedAnswerIndex(index)
            if (selectedAnswerIndex != null) {
                val choiceCount = answerChoiceCounts.getOrDefault(selectedAnswerIndex, 0)
                answerChoiceCounts[selectedAnswerIndex] = choiceCount + 1
            }
        }
        HorizontalBarChartWithText(scoreViewModel)

        for ((choiceIndex, count) in answerChoiceCounts) {
            Text(
                text = "Choice $choiceIndex: Total Score = ${count * 20}",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
    }
}

@Composable
fun HorizontalBarChartWithText(scoreViewModel: ScoreViewModel) {
    val iCount = scoreViewModel.getSelectedAnswerCounts(0)
    val eCount = scoreViewModel.getSelectedAnswerCounts(1)
    val totalCount = iCount + eCount

