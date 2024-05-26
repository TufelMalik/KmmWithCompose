package database.local

import classes.toUsers
import com.tufelmalik.composekmm.AppDatabase
import model.Users

class SqlDelightUsersDataSource(db: AppDatabase) : UsersDataSource {
    private val queries = db.appDatabaseQueries

    override suspend fun insertUser(users: Users) {
        queries.insertUser(
            id = users.id,
            name = users.name!!,
            email = users.email!!,
            password = users.password!!,
            address = users.address
        )
    }

    override suspend fun getUserById(id: Long): Users {
        return queries.selectUserByUserId(
            id = id
        ).executeAsOneOrNull()!!.toUsers()
    }

    override suspend fun getAllUsers(): List<Users> {
        return queries.selectAllUsers().executeAsList().map {
            it.toUsers()
        }
    }

    override suspend fun deleteUserById(id: Long) {
        queries.deleteUserByUserId(id)
    }


}