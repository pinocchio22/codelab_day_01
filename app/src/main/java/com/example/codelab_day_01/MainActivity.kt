package com.example.codelab_day_01

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.codelab_day_01.ui.theme.Codelab_day_01Theme
import com.example.codelab_day_01.ui.theme.Green200
import com.example.codelab_day_01.ui.theme.Teal200
import com.example.codelab_day_01.ui.theme.Yellow200

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Codelab_day_01Theme {
                // A surface container using the 'background' color from the theme
                MyApp()
//                MyColumn()
            }
        }
    }
}

@Composable
private fun MyApp() {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    if (shouldShowOnboarding) {
        OnboardingScreen(onContinueClicked = {
            Log.d("TAG", "MyApp: OnBoarding Click")
            shouldShowOnboarding = !shouldShowOnboarding
        })
    } else {
//        MyColumn(modifier = Modifier.padding(20.dp))
        MyLazyColumn(modifier = Modifier.padding(20.dp))
    }
    
//    Surface(color = Green200) {
////        Hello(modifier = Modifier.padding(10.dp))
//        MyColumn(modifier = Modifier.padding(10.dp))
//    }
}

@Composable
fun MyColumn(numbers: List<String> = listOf("1번", "2번", "3번"), modifier: Modifier) {
    Column(modifier = modifier) {
        for (num in numbers) {
            NumberView(name = num)
        }
    }
}

@Composable
fun MyLazyColumn(numbers: List<String> = List(50){"번호 : $it"}, modifier: Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = numbers) {
            NumberView(name = it)
        }
    }
}

@Composable
fun NumberView(name: String) {

    val expanded = rememberSaveable { mutableStateOf(false) }
    var isOpen by rememberSaveable { mutableStateOf(false) }

//    val extraPadding = if (expanded.value) 48.dp else 0.dp

    val extraPadding by animateDpAsState(if (expanded.value) 48.dp else 0.dp)

    Surface(color = Yellow200,
        modifier = Modifier
            .padding(bottom = extraPadding)
            .clickable {
                Log.d("TAG", "NumberView : $name")
            }) {
        Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(end = 10.dp)
            .fillMaxWidth(1f)) {
            Text(text = name,
                modifier = Modifier
                    .padding(10.dp)
                    .background(Teal200)
                    .weight(0.5f),
                style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            OutlinedButton(
//                modifier = Modifier.weight(0.5f),
                onClick = {
                    Log.d("TAG", "NumberView: 버튼클릭 $name")
                    // 1번
                    expanded.value = !expanded.value
//                    // 2번
//                    isOpen = !isOpen
                }
            ) {
//                // 1번
                Text(if (expanded.value) "열렸다" else "닫혔다")
//                Text(text = if (isOpen) "열렸다" else "닫혔다")
            }
        }
    }
}

@Composable
private fun Greeting(name: String) {
    Surface(color = MaterialTheme.colors.primary) {
        Text (text = "Hello $name!")
    }
}

@Composable
fun Hello(modifier: Modifier) {
    Surface(color = Green200, modifier = modifier) {
        Text(text = "Hello World", color = Color.White, modifier = Modifier.padding(30.dp))
    }
}

@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit) {
    
    var shouldShowOnboarding by remember { mutableStateOf(true) }

    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to the Basics Codelab!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked
            ) {
                Text("Continue")
            }
        }
    }
}
@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)

@Composable
fun DefaultPreview() {
    Codelab_day_01Theme {
        MyLazyColumn(modifier = Modifier.padding(20.dp))
    }
}