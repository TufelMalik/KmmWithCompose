import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import composekmm.composeapp.generated.resources.Res
import composekmm.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import screens.ManageUserListScreen
import screens.ManageUsersScreen
import viewmodel.MainViewModel

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    MaterialTheme {

//    https://github.com/patrickdip/KMMMovieApp/blob/main/shared/src/commonMain/kotlin/com/dipumba/movies/di/SharedModules.kt

        val navController = rememberNavController()
        NavHost(
            navController = navController, startDestination = "ManageUsersScreen"
        ) {
            composable("screen1") {
                val viewModel = viewModel<MainViewModel> {
                    MainViewModel("Tufel Malik")
                }
                val timer by viewModel.timer.collectAsState()
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Image(
                            painter = painterResource(Res.drawable.compose_multiplatform),
                            null,
                            modifier = Modifier.width(180.dp).height(180.dp)
                        )
                        Text(text = "Count : $timer", fontSize = 30.sp, color = Color.Red)
                    }

                }

            }

            composable("ManageUsersScreen") {
                ManageUsersScreen(navController)
            }

            composable("ManageUserListScreen") {
                ManageUserListScreen()
            }


        }
    }
}

// https://github.com/jshvarts/KmpGithubMVVM
// https://api.github.com/orgs/jetbrains/members