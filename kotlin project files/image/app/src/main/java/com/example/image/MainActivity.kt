package com.example.image

import android.graphics.Bitmap
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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.image.ui.theme.ImageTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageTheme {
                MainScreen()
            }
        }
    }
}



@Composable
fun MainScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()
        ) {
            var selectUri by remember {
                mutableStateOf<List<Uri?>>(emptyList())
            }
            val launcher =
                rememberLauncherForActivityResult(contract = ActivityResultContracts.PickMultipleVisualMedia(
                    5
                ), onResult = { uris ->
                    selectUri = uris
                })

            val context = LocalContext.current
            val defaultBitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                ImageDecoder.decodeBitmap(
                    ImageDecoder.createSource(
                        context.contentResolver,
                        Uri.parse("android.resource://${context.packageName}/drawable/cat")
                    )
                )
            } else {
                MediaStore.Images.Media.getBitmap(
                    context.contentResolver,
                    Uri.parse("android.resource://${context.packageName}/drawable/cat")
                )
            }

            val bitmaps: List<Bitmap> = selectUri.mapNotNull { uri ->
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    ImageDecoder.decodeBitmap(
                        ImageDecoder.createSource(
                            context.contentResolver, uri!!
                        )
                    )
                } else {
                    MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
                }
            }

            val bitmap = bitmaps.firstOrNull() ?: defaultBitmap


            if (selectUri.isEmpty()) {
                Image(
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = "",
                    modifier = Modifier
                        .size(360.dp)
                        .shadow(10.dp, shape = MaterialTheme.shapes.large)
                        .background(MaterialTheme.colorScheme.background),
                    contentScale = ContentScale.Crop
                )
            } else {
                LazyRow(
                    modifier = Modifier
                        .padding(20.dp)
                        .size(360.dp)
                        .shadow(10.dp, shape = MaterialTheme.shapes.large)
                ) {
                    items(bitmaps) { image ->
                        Image(
                            bitmap = image.asImageBitmap(),
                            contentDescription = "",
                            modifier = Modifier
                                .size(360.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }


            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
                },
                modifier = Modifier
                    .padding(10.dp)
                    .size(80.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White

                )
            ) {
                Text(text = "변경")

            }

            Button(
                onClick = {
                    selectUri = emptyList()
                },
                modifier = Modifier
                    .padding(10.dp)
                    .size(80.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White
                )
            ) {
                Text(text = "냐옹")
            }
        }
    }
}
