package com.example.imagetotext

import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imagetotext.ui.theme.ImagetotextTheme
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.korean.KoreanTextRecognizerOptions
import java.io.IOException

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImagetotextTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Mainfun()
                }
            }
        }
    }
}


@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Mainfun() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()
    ) {
        var selectUri by remember {
            mutableStateOf<Uri?>(null)
        }
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { uri ->
                selectUri = uri
            })


        val context = LocalContext.current

        val bitmap = selectUri?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                ImageDecoder.decodeBitmap(
                    ImageDecoder.createSource(
                        context.contentResolver, it
                    )
                )
            } else {
                MediaStore.Images.Media.getBitmap(context.contentResolver, it)
            }
        }

        val recognizer = TextRecognition.getClient(KoreanTextRecognizerOptions.Builder().build())


        val translatedText by remember { mutableStateOf("") }
        var textTranstlated by remember { mutableStateOf("") }

        val koEnTranslator = remember {
            val options = TranslatorOptions.Builder().setSourceLanguage(TranslateLanguage.KOREAN)
                .setTargetLanguage(TranslateLanguage.ENGLISH).build()
            Translation.getClient(options)
        }
        var enabled by remember {
            mutableStateOf(false)
        }

        LaunchedEffect(Unit) {
            val conditions = DownloadConditions.Builder().requireWifi().build()
            koEnTranslator.downloadModelIfNeeded(conditions).addOnSuccessListener {
                enabled = true
            }
        }






        Box(
            modifier = Modifier
                .padding(15.dp)
                .size(200.dp)
                .shadow(10.dp, shape = MaterialTheme.shapes.large)
        ) {
            if (bitmap != null) {
                Image(
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    contentScale = ContentScale.Crop
                )
            }
        }

        Spacer(modifier = Modifier.height(3.dp))

        Button(
            onClick = {
                launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            },
            modifier = Modifier
                .padding(10.dp)
                .width(130.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White
            )
        ) {
            Text(text = "이미지 입력")
        }

        Spacer(modifier = Modifier.height(3.dp))


        var extractedText by remember { mutableStateOf("") }

        // 버튼을 클릭하여 이미지를 선택하면 아래의 코드가 실행됩니다.
        if (selectUri != null) {
            val image: InputImage
            try {
                image = InputImage.fromFilePath(context, selectUri!!)
                recognizer.process(image).addOnSuccessListener { visionText ->
                    extractedText = visionText.text // 추출된 텍스트
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        // 텍스트가 있을 경우에만 출력
        if (extractedText.isNotEmpty()) {
            Text(
                text = extractedText, modifier = Modifier.padding(10.dp), color = Color.Black
            )
        }

        Button(
            onClick = {
                koEnTranslator.translate(extractedText).addOnSuccessListener { extractedText ->
                        textTranstlated = extractedText

                    }
            },
            enabled = enabled,
            modifier = Modifier
                .padding(20.dp)
                .size(width = 130.dp, height = 80.dp)
        ) {
            Text(text = "번역", fontSize = 18.sp)
        }
        Box(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .height(300.dp)
                .shadow(10.dp, shape = MaterialTheme.shapes.large)
                .background(Color.White),
        ) {
            Text(
                text = textTranstlated.ifBlank { "" },
                fontSize = 25.sp,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}


