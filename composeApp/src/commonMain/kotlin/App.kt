import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composekmm.composeapp.generated.resources.Res
import composekmm.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    MaterialTheme {

        var name by remember { mutableStateOf("Tufel") }
        var showContent by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = {
                showContent = !showContent
                name = if (showContent) {
                    "Tufel Malik "
                } else {
                    "Tufel Malik { Developer }"
                }
            }) {
                Text("Click me!")
            }

            Text(
                text = name,
                modifier = Modifier.fillMaxWidth().background(Color.Black)
                    .padding(vertical = 10.dp),
                color = Color.White,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
            Image(
                painter = painterResource(Res.drawable.compose_multiplatform), // Adjust based on actual resource
                contentDescription = null,
                modifier = Modifier
                    .width(100.dp)
                    .height(90.dp)
                    .clickable {
                        name = "Tufel Malik."
                    }
            )


            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painterResource(Res.drawable.compose_multiplatform),
                        null,
                        modifier = Modifier.width(200.dp).height(200.dp)
                    )
                    Text(
                        "Compose: $greeting",
                        modifier = Modifier.padding(vertical = 10.dp)
                            .background(Color.Gray).fillMaxSize(),
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        )
                }
            }
        }
    }
}

// https://github.com/jshvarts/KmpGithubMVVM
// https://api.github.com/orgs/jetbrains/members