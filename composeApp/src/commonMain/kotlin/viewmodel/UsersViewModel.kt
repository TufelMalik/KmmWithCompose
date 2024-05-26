package viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import model.Users
import repositories.UsersRepositoryImp

class UsersViewModel(private val repository: UsersRepositoryImp) : ViewModel() {
    private val _usersData = MutableStateFlow<List<Users>>(emptyList())
    val usersLiveData: StateFlow<List<Users>> = _usersData


    fun insertUser(users: Users) {
        viewModelScope.launch {
            repository.insertUser(users)
            loadUsersData()
        }
    }

    fun getAllUsers() {
        viewModelScope.launch {
            repository.getAllUsers()
        }
    }

    fun getUserById(id: Long) {
        viewModelScope.launch {
            repository.getUserById(id)
        }
    }

    fun deleteUserById(id: Long) {
        viewModelScope.launch {
            repository.deleteUserById(id)
            loadUsersData()
        }
    }

    private fun loadUsersData() {
        viewModelScope.launch {
            _usersData.value = repository.getAllUsers()
        }
    }


}