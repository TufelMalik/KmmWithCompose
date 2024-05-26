package screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import classes.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import model.Users
import viewmodel.UsersViewModel

@Composable
fun ManageUserListScreen() {
    val usersViewModel = Utils.provideUsersViewModel()
    val users = usersViewModel.usersLiveData.collectAsState().value
    CoroutineScope(Dispatchers.IO).launch {
        usersViewModel.getAllUsers()
        val a = usersViewModel.usersLiveData.value
        println("\n\n\nUsersList data :$a \n\n")
    }
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(users) { user ->
            UserListItem(user, usersViewModel)
        }
    }
}

@Composable
private fun UserListItem(user: Users, viewModel: UsersViewModel) {
    val randomColor = Users.generateRandomColor()

    Box(
        modifier = Modifier.background(color = randomColor, shape = RoundedCornerShape(20.dp))
            .padding(10.dp)
            .clickable {
                println("Clicked item id : ${user.id}")
            }
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                text = "Name : ${user.name!!}", fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = Color.White,
                textDecoration = TextDecoration.Underline
            )

            Spacer(modifier = Modifier.padding(vertical = 10.dp))

            Text(
                text = "Email : ${user.email!!}", fontSize = 15.sp,
                textAlign = TextAlign.Start,
                color = Color.White
            )

            Spacer(modifier = Modifier.padding(vertical = 10.dp))

            Text(
                text = "Password : ${user.password!!}", fontSize = 15.sp,
                textAlign = TextAlign.Start,
                color = Color.White
            )

            Spacer(modifier = Modifier.padding(vertical = 10.dp))

            Text(
                text = "Address : ${user.address!!}", fontSize = 15.sp,
                textAlign = TextAlign.Start,
                color = Color.White
            )

            Spacer(modifier = Modifier.padding(top = 30.dp))

            Button(
                onClick = {
                    deleteSelectedItemById(user.id, viewModel)
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                modifier = Modifier.fillMaxWidth().padding(10.dp), shape = RoundedCornerShape(20.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = "Delete",
                    tint = Color.Red,
                    modifier = Modifier.padding(end = 15.dp)
                )
                Text(text = "Delete", color = Color.Black)
            }
        }
    }
}

fun deleteSelectedItemById(id: Long?, viewModel: UsersViewModel) {
    CoroutineScope(Dispatchers.IO).launch {
        viewModel.deleteUserById(id!!)
    }
}
