package screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import classes.Utils
import classes.Utils.provideUsersViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import model.Users


@Composable
fun ManageUsersScreen(navController: NavHostController) {

    val usersViewModel = provideUsersViewModel()
    val users = usersViewModel.usersLiveData.collectAsState()

    println("Users List : $users")

    val focus = LocalFocusManager.current
    val scrollState = rememberScrollState()

    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    var address by rememberSaveable { mutableStateOf("") }

    var validName by rememberSaveable { mutableStateOf(true) }
    var validEmail by rememberSaveable { mutableStateOf(true) }
    var validPassword by rememberSaveable { mutableStateOf(true) }
    var validConfirmPassword by rememberSaveable { mutableStateOf(true) }
    var validAddress by rememberSaveable { mutableStateOf(true) }
    var isAllInputVerified by rememberSaveable { mutableStateOf(false) }


    val validNameError by rememberSaveable { mutableStateOf("Name is required!") }
    val validEmailError by rememberSaveable { mutableStateOf("Email is required!") }
    val validPasswordError by rememberSaveable { mutableStateOf("Password is required!") }
    val validConfirmPasswordError by rememberSaveable { mutableStateOf("Password must be equal!") }
    val validAddressError by rememberSaveable { mutableStateOf("Address is required!") }


    fun validateData(
        name: String,
        email: String,
        password: String,
        confirmPassword: String,
        address: String
    ): Boolean {
        validName = name.isNotEmpty()
        validEmail = email.isNotEmpty()
        validPassword = password.isNotEmpty()
        validConfirmPassword = password == confirmPassword
        validAddress = address.isNotEmpty()

        return validName && validEmail && validPassword && validConfirmPassword && validAddress
    }


    fun saveUserData(
        name: String,
        email: String,
        password: String,
        confirmPassword: String,
        address: String
    ) {
        if (validateData(name, email, password, confirmPassword, address)) {
            println("Data hase been saved successfully!")
            usersViewModel.insertUser(
                Users(
                    name = name, email = email, password = password, address = address
                )
            )
            CoroutineScope(Dispatchers.IO).launch {
                val userData = usersViewModel.usersLiveData.value
                println("\n\n\nData :$userData \n\n")
            }
            isAllInputVerified = true
        } else {
            println("Failed to save data!")
            isAllInputVerified = false
        }

    }

    val rainbowColors: List<Color> = listOf(
        Utils.VioletHex,
        Utils.LightGreenHex,
//        Utils.BabyBlueHex,
//        Utils.RedPinkHex,
//        Utils.RedOrangeHex
    )
    val brush = remember {
        Brush.linearGradient(colors = rainbowColors)
    }
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(scrollState)
            .background(brush = brush)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Register to out app",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(vertical = 25.dp),
            color = Color.White,
        )


        CustomOutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = "Name",
            showError = !validName,
            errorMessage = validNameError,
            leadingIconImageVector = Icons.Default.Person,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focus.moveFocus(FocusDirection.Down) }
            ),
        )


        CustomOutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = "Email Address",
            showError = !validEmail,
            errorMessage = validEmailError,
            leadingIconImageVector = Icons.Default.Email,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focus.moveFocus(FocusDirection.Down) }
            ),
        )

        CustomOutlinedTextField(
            value = password,
            onValueChange = { password = it },
            isPasswordVisible = true,
            label = "Password",
            isPasswordField = true,
            showError = !validPassword,
            errorMessage = validPasswordError,
            leadingIconImageVector = Icons.Default.Lock,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focus.moveFocus(FocusDirection.Down) }
            ),
        )


        CustomOutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            isPasswordField = true,
            label = "Confirm Password",
            isPasswordVisible = true,
            showError = !validConfirmPassword,
            errorMessage = validConfirmPasswordError,
            leadingIconImageVector = Icons.Default.Lock,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focus.moveFocus(FocusDirection.Down) }
            ),
        )

        CustomOutlinedTextField(
            value = address,
            onValueChange = { address = it },
            label = "Address",
            showError = !validAddress,
            errorMessage = validAddressError,
            leadingIconImageVector = Icons.Default.LocationOn,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focus.moveFocus(FocusDirection.Down) }
            ),
        )


        Button(
            onClick = {
                saveUserData(name, email, password, confirmPassword, address)
            },
            modifier = Modifier.padding(vertical = 20.dp),
            shape = RoundedCornerShape(20.dp),
            content = { Text("Validate Input") }
        )
        Button(
            onClick = {
                navController.navigate("ManageUserListScreen")
            },
            modifier = Modifier.padding(vertical = 20.dp),
            shape = RoundedCornerShape(20.dp),
            content = { Text("Manage Users List") }
        )

        AnimatedVisibility(
            visible = isAllInputVerified,
            enter = fadeIn(
                // Overwrites the initial value of alpha to 0.4f for fade in, 0 by default
                initialAlpha = 0.4f
            ),
            exit = fadeOut(
                // Overwrites the default animation with tween
                animationSpec = tween(durationMillis = 250)
            )
        ) {
            Text(
                text = "Data saved successfully.",
                textAlign = TextAlign.Center,
                fontSize = 25.sp,
                modifier = Modifier.padding(top = 5.dp),
                color = Color.Green
            )
        }


    }
}

