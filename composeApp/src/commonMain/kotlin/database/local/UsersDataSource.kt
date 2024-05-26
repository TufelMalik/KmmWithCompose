package database.local

import model.Users


interface UsersDataSource {
    suspend fun insertUser(users: Users)
    suspend fun getUserById(id: Long): Users?
    suspend fun getAllUsers(): List<Users>
    suspend fun deleteUserById(id: Long)
}