package screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    leadingIconImageVector: ImageVector,
    leadingIconDescription: String = "",
    isPasswordField: Boolean = false,
    isPasswordVisible: Boolean = false,
    onVisibleChange: (Boolean) -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    showError: Boolean = false,
    errorMessage: String = ""

) {
    val rainbowColors: List<Color> = listOf(
        Color.Blue,
        Color.Magenta,
        Color.Green,
        Color.Blue,
        Color.Magenta,
        Color.Green,
        Color.Blue,
        Color.Magenta,
        Color.Green,
    )
    val brush = remember {
        Brush.linearGradient(colors = rainbowColors)
    }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        OutlinedTextField(
            value = value,
            textStyle = TextStyle(brush = brush),
            onValueChange = { onValueChange(it) },
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp,bottom = 10.dp),
            label = { Text(text = label) },
            leadingIcon = {
                Icon(
                    imageVector = leadingIconImageVector,
                    contentDescription = leadingIconDescription,
                    tint = if (showError) MaterialTheme.colors.error else MaterialTheme.colors.onSurface
                )
            },
            isError = showError,
            trailingIcon = {
                if (showError && !isPasswordField) {
                    Icon(
                        imageVector = Icons.Filled.Warning,
                        contentDescription = "Show error icon"
                    )
                }
                if (isPasswordField) {
                    IconButton(
                        onClick = { onVisibleChange(!isPasswordVisible) }
                    ) {
                        Icon(
                            imageVector = if (isPasswordVisible) Icons.Default.Lock else Icons.Filled.ShoppingCart,
                            contentDescription = "Toggle Password Visibility"
                        )
                    }
                }
            },
            visualTransformation = when {
                isPasswordField && isPasswordVisible -> VisualTransformation.None
                isPasswordField -> PasswordVisualTransformation()
                else -> VisualTransformation.None
            },
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = true
        )

        AnimatedVisibility(
            visible = showError,
            enter = fadeIn(
                initialAlpha = 0.4f
            ),
            exit = fadeOut(
                animationSpec = tween(durationMillis = 250)
            )
        ) {
            Text(
                text = errorMessage,
                modifier = Modifier.fillMaxWidth().padding(start = 8.dp)
                    .offset(y = ((-8).dp))
                    .fillMaxWidth(),
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
            )
        }
    }
}


/*
 AnimatedVisibility(
            visible = showError,
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
                text = "Please enter all data first!",
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                color = Color.Red,
                fontSize = 14.sp,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center,
            )
        }
 */