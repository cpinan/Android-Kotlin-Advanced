package com.carlospinan.livepreviewcompose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.ambient
import androidx.ui.core.Alignment
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.foundation.SimpleImage
import androidx.ui.foundation.shape.corner.CircleShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.imageFromResource
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.surface.Surface
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PreviewDarkTutorialTemplate()
        }
    }
}

// TUTORIAL PREVIEW
@Composable
fun TutorialTemplate(
    colors: ColorPalette = lightColorPalette(),
    typography: Typography = Typography()
) {
    val post = Post(
        title = "Dagger in Kotlin: Gotchas and Optimizations",
        author = "Manuel Vivo",
        date = "July 30",
        timeReading = 3,
        resource = R.drawable.preview
    )

    MaterialTheme(
        colors = colors,
        typography = typography
    ) {
        Scaffold(
            topAppBar = { TopAppBar({ Text("TOP APP BAR") }) },
            bottomAppBar = { BottomAppBar<Any>(fabConfiguration = it, cutoutShape = CircleShape) },
            bodyContent = {
                Surface {
                    PostCardTop(post)
                }
            }
        )
    }
}

@Composable
fun PostCardTop(post: Post) {

    val context = ambient(key = ContextAmbient)

    Container(
        modifier = LayoutPadding(16.dp),
        alignment = Alignment.TopCenter
    ) {
        Column(
            modifier = LayoutWidth.Fill,
            arrangement = Arrangement.Begin
        ) {

            Container(
                modifier = LayoutWidth.Fill,
                height = 160.dp
            ) {
                SimpleImage(image = imageFromResource(context.resources, post.resource))
            }

            Text(text = post.title, style = MaterialTheme.typography().h5)
            Text(text = post.author, style = MaterialTheme.typography().subtitle1)

            Row {
                Text(
                    text = "${post.date} - ${post.timeReading} min read",
                    style = MaterialTheme.typography().caption.copy(
                        color = Color.Gray
                    )
                )
            }


        }
    }
}

@Preview("Default Colors")
@Composable
fun PreviewLightTutorialTemplate() {
    TutorialTemplate()
}

@Preview("Dark Colors")
@Composable
fun PreviewDarkTutorialTemplate() {
    TutorialTemplate(
        colors = darkColorPalette()
    )
}

data class Post(
    val title: String,
    val author: String,
    val date: String,
    val timeReading: Int,
    val resource: Int
)