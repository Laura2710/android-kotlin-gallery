package eni.fr.gallerie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eni.fr.gallerie.ui.theme.GallerieTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GallerieTheme {
                GalleryApp(Modifier.fillMaxWidth())
            }
        }
    }
}

@Composable
fun GalleryApp(modifier: Modifier = Modifier) {
    var position by remember { mutableStateOf(1) }
    position = when {
        position > 3 -> 1
        position < 1 -> 3
        else -> position
    }
    val img = when (position) {
        1 -> R.drawable.img1
        2 -> R.drawable.img2
        3 -> R.drawable.img3
        else -> R.drawable.img1
    }
    val titreImg = when (position) {
        1 -> R.string.desc_img_1
        2 -> R.string.desc_img_2
        3 -> R.string.desc_img_3
        else -> R.string.desc_img_1
    }

    Text(
        text = "GALLERY PROJECT",
        textAlign = TextAlign.Center,
        color = Color.White,
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF4A148C), Color(0xFF1E88E5))
                )
            )
            .padding(vertical = 24.dp)
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color(0xFFF5F5F5))
            .padding(16.dp)
    ) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            shadowElevation = 8.dp,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = img),
                contentDescription = null,
                modifier = Modifier
                    .height(300.dp)
                    .padding(16.dp),
                contentScale = ContentScale.Fit,
            )
        }

        Text(
            text = stringResource(id = titreImg),
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xFF3E2723),
            modifier = Modifier.padding(top = 16.dp)
        )

        Spacer(modifier = Modifier.height(50.dp))
        NavigationBarButton(
            position = position,
            onPositionChange = { newPosition: Int -> position = newPosition }
        )
        Spacer(modifier = Modifier.height(20.dp))
    }
}

fun detectHorizontalDragGestures(any: Any) {

}

@Composable
fun NavigationBarButton(
    position: Int = 1,
    onPositionChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = { onPositionChange(position - 1) },
            modifier = Modifier.size(150.dp, 50.dp)
        ) {
            Text(text = "Précédent")
        }
        Button(
            onClick = { onPositionChange(position + 1) },
            modifier = Modifier.size(150.dp, 50.dp)
        ) {
            Text(text = "Suivant")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GalleryAppPreview() {
    GallerieTheme {
        GalleryApp()
    }
}
