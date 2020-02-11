package com.carlospinan.jetpackcomposer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.Model
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.LayoutHeight
import androidx.ui.layout.LayoutPadding
import androidx.ui.material.*
import androidx.ui.material.surface.Surface
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MyScreenContent()
            }
        }
    }
}

@Composable
fun MyApp(children: @Composable() () -> Unit) {
    MaterialTheme {
        Surface(color = Color.Yellow) {
            children()
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!", modifier = LayoutPadding(24.dp))
}

@Composable
fun MyScreenContent(
    names: List<String> = listOf("Android", "there"),
    counterState: CounterState = CounterState()
) {
    /*
    Column {
        for (name in names) {
            Greeting(name = "$name")
            Divider(color = Color.Black)
        }
        Divider(color = Color.Transparent, height = 32.dp)
        Counter(counterState)
    }
     */
    Column(
        modifier = LayoutHeight.Fill
    ) {
        Column(modifier = LayoutFlexible(flex = 1.0f)) {
            for (name in names) {
                Greeting(name = "$name")
                Divider(color = Color.Black)
            }
        }
        Counter(counterState)
    }
}


@Composable
fun Counter(state: CounterState) {
    Container(
        modifier = LayoutPadding(16.dp)
    ) {
        Button(
            text = "I've been clicked ${state.count} times",
            onClick = { state.count++ }
        )
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApp {
        Greeting(name = "Android")
    }
}

@Preview("Test My Screen Content Preview")
@Composable
fun PreviewMyScreenContent() {
    MyApp {
        MyScreenContent()
    }
}

@Composable
fun Form(formState: FormState) {
    Checkbox(
        checked = formState.optionChecked,
        onCheckedChange = { newState ->
            formState.optionChecked = newState
        }
    )
}


// MODEL
@Model
class CounterState(var count: Int = 0)

@Model
class FormState(var optionChecked: Boolean)