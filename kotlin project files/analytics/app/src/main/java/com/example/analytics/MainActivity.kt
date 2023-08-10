package com.example.analytics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase


class MainActivity : ComponentActivity() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firebaseAnalytics = Firebase.analytics


        // 로그인 이벤트
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.METHOD, "method")
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN, bundle)

        // 콘텐츠 조회 이벤트
        val contentViewParams = Bundle().apply {
            putString("contentId", "1234567890")
            putString("contentType", "video")
            putString("contentTitle", "Title of the video")
        }
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, contentViewParams)



        @Composable
        fun mm() {
            LaunchedEffect(key1 = firebaseAnalytics) {
                // 이벤트를 기록합니다.
                val mmParams = Bundle().apply {
                    putString("mm", "mm")
                }
                firebaseAnalytics.logEvent("mm", mmParams)
            }

            Button(
                onClick = {
                    val buttonClickParams = Bundle().apply {
                        putString("buttonId", "1234567890")
                        putString("buttonLabel", "Label of the button")
                    }
                    firebaseAnalytics.logEvent("buttonClick", buttonClickParams)
                }, modifier = Modifier.padding(16.dp)
            ) {
                // 버튼 내용
                Text(text = "Click Me")
            }
        }
    }
}