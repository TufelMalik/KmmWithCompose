package database.local

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import classes.MyApplication
import com.tufelmalik.composekmm.AppDatabase

actual class DriverFactory{
    private var context: Context = MyApplication.context!!
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(AppDatabase.Schema, this.context, "tufel.db")
    }
}