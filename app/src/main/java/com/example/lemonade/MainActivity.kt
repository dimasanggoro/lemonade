package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonApp()
                }
            }
        }
    }
}

@Composable
fun LemonApp(modifier: Modifier = Modifier) {
    var currentStage by remember {
        mutableIntStateOf(0)
    }
    val requiredSqueeze = (2..4).random()
    var squeezes = 0

    when (currentStage) {
        0 -> CurrentStage(
            modifier = modifier,
            instruction = R.string.lemon_tree,
            imageResId = R.drawable.lemon_tree,
            contentDescription = R.string.lemon_tree_content_description,
            onClickHandler = {
                currentStage++
            }
        )

        1 -> CurrentStage(
            modifier = modifier,
            instruction = R.string.lemon,
            imageResId = R.drawable.lemon_squeeze,
            contentDescription = R.string.lemon_content_description,
            onClickHandler = {
                squeezes++
                if (squeezes == requiredSqueeze) currentStage++
            }
        )

        2 -> CurrentStage(
            modifier = modifier,
            instruction = R.string.lemonade,
            imageResId = R.drawable.lemon_drink,
            contentDescription = R.string.lemonade_content_description,
            onClickHandler = {
                currentStage++
            }
        )

        3 -> CurrentStage(
            modifier = modifier,
            instruction = R.string.empty_glass,
            imageResId = R.drawable.lemon_restart,
            contentDescription = R.string.empty_glass_content_description,
            onClickHandler = {
                currentStage = 0
            }
        )
    }
}

@Composable
fun CurrentStage(modifier: Modifier,
                 instruction: Int,
                 imageResId: Int,
                 contentDescription: Int,
                 onClickHandler: () -> Unit) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onClickHandler) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = stringResource(contentDescription)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = instruction),
            fontSize = 18.sp
        )
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonApp()
    }
}






