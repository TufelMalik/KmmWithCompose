package repositories

import database.local.SqlDelightUsersDataSource
import model.Users
import repositories.repositoryImp.UsersRepository


class UsersRepositoryImp(private val usersDataSource: SqlDelightUsersDataSource) : UsersRepository {

    override suspend fun insertUser(user: Users) {
        usersDataSource.insertUser(user)
    }

    override suspend fun getUserById(id: Long): Users {
        return usersDataSource.getUserById(id)
    }

    override suspend fun getAllUsers(): List<Users> {
        return usersDataSource.getAllUsers()
    }

    override suspend fun deleteUserById(id: Long) {
        usersDataSource.deleteUserById(id)
    }
}