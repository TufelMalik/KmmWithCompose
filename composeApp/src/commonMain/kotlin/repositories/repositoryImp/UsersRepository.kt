package repositories.repositoryImp

import model.Users

interface UsersRepository {
    suspend fun insertUser(user: Users)
    suspend fun getUserById(id: Long): Users?
    suspend fun getAllUsers(): List<Users>
    suspend fun deleteUserById(id: Long)

}