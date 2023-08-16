// build.gradle module에 아래 dependencis에 아래 코드 추가  / 아래 특정 코드는 rememberNavController() , composable() 추가를 위해서 필요

//   val nav_version = "2.5.3"
//   implementation("androidx.navigation:navigation-compose:$nav_version")

//   implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
//   implementation("com.attafitamim.navigation:android:1.0.0-beta01")
//   implementation("androidx.navigation:navigation-runtime-ktx:2.5.3")

package com.example.comp_nav_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.comp_nav_test.ui.theme.Comp_nav_testTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Comp_nav_testTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun Screen1(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Screen 1")
        Spacer(modifier = Modifier.height(16.dp))

// 아래 람다식 네비 버튼을 풀어서 쓴 경우
//        fun onClickFunction() {
//            navController.navigate("screen2/parameter")
//        }
//
//        Button(onClick = { onClickFunction() }) {
//            Text(text = "Go to Screen 2")
//        }

        Button(onClick = { navController.navigate("screen2/parameter") }) {
            Text(text = "Go to Screen 2")
        }
    }
}

@Composable
fun Screen2(navController: NavController, parameter: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Screen 2")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Parameter from Screen 1: $parameter")
        Spacer(modifier = Modifier.height(16.dp))
// popBackStack(): 현재 화면을 스택에서 제거하고 이전 화면으로 돌아갑니다.
        Button(onClick = { navController.popBackStack() }) {
            Text(text = "뒤로")
        }
        Button(onClick = { navController.navigate("screen3") }) {
            Text(text = "다음")
        }
    }
}

@Composable
fun AppNavigation() {
// 네비게이션 컨트롤러를 생성하고 상태 보존을 위해 rememberNavController()를 사용
    val navController = rememberNavController()
// NavHost를 사용하여 네비게이션 호스트를 구성
    NavHost(
 // 생성한 네비게이션 컨트롤러를 사용
        navController = navController,
// 시작 화면을 "screen1"로 설정
        startDestination = "screen1"
    ) {
// "screen1" 경로에 해당하는 화면을 정의하고, Screen1 Composable 함수를 호출
        composable("screen1") { Screen1(navController) }
 // "screen2/{parameter}" 경로에 해당하는 화면을 정의,
 // 네비게이션 인자로 "parameter"를 받아와 Screen2 Composable 함수를 호출
        composable(
            "screen2/{parameter}",
            arguments = listOf(navArgument("parameter") { type = NavType.StringType })
        ) { backStackEntry ->
// 현재 엔트리의 네비게이션 인자에서 "parameter" 값을 추출
            val parameter = backStackEntry.arguments?.getString("parameter")
// 추출한 "parameter" 값을 사용하여 Screen2 Composable 함수를 호출
            Screen2(navController, parameter ?: "")
        }
    }
}

//parameter를 꼭 안써도 되는것같아서 제거 ver 연구중 




