package di

import app.cash.sqldelight.db.SqlDriver
import com.tufelmalik.composekmm.AppDatabase
import database.local.DriverFactory
import database.local.SqlDelightUsersDataSource
import org.koin.dsl.module
import repositories.UsersRepositoryImp
import repositories.repositoryImp.UsersRepository

val databaseModule = module {
    single<SqlDriver> { DriverFactory().createDriver() }
    single { AppDatabase(get()) }
    single { SqlDelightUsersDataSource(get()) }
    single<UsersRepository> { UsersRepositoryImp(get()) }
}
