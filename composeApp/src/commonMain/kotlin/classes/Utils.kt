package classes

import androidx.compose.ui.graphics.Color
import com.tufelmalik.composekmm.AppDatabase
import database.local.DriverFactory
import database.local.SqlDelightUsersDataSource
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import repositories.UsersRepositoryImp
import viewmodel.UsersViewModel

object Utils {
    const val movieBaseUrl = "https://api.themoviedb.org/3"
    const val movieApiKey = "be1a4a3a0d10997f9651b488068efee0"
    const val movieToken =
        "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiZTFhNGEzYTBkMTA5OTdmOTY1MWI0ODgwNjhlZmVlMCIsInN1YiI6IjY1MDZmNzk2M2NkMTJjMDBjYTU1Y2JlZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Bqc4VcroORhvIA-odRvRPrvv1ocGEUa-baHS4sB6124"

    private const val sqlCodeGenerator = "./gradlew generateCommonMainAppDatabaseInterface"

    val RedOrangeHex: Color = Color(0xffffab914da)
    val BabyBlueHex: Color = Color(0xff81deea)
    val VioletHex: Color = Color(0xffcf94da)
    val LightGreenHex: Color = Color(0xffe7ed9b)
    val RedPinkHex: Color = Color(0xfff48fb1)
    val LightBlueHex: Color = Color(0xff81deea)
    val Violet2Hex: Color = Color(0xffcf94da)
    val LightGreen2Hex: Color = Color(0xffe7ed9b)
    val RedPink2Hex: Color = Color(0xfff48fb1)
    val LightBlue2Hex: Color = Color(0xff81deea)
    val Violet3Hex: Color = Color(0xffcf94da)
    val LightGreen3Hex: Color = Color(0xffe7ed9b)
    val RedPink3Hex: Color = Color(0xfff48fb1)


    fun provideUsersViewModel(): UsersViewModel {
        val driver = DriverFactory().createDriver()
        val database = AppDatabase(driver)
        val usersDataSource = SqlDelightUsersDataSource(database)
        val usersRepository = UsersRepositoryImp(usersDataSource)
        return UsersViewModel(usersRepository)
    }

    fun now(): LocalDateTime {
        return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    }

    fun toEpochMillis(dateTime: LocalDateTime): Long {
        return dateTime.toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()
    }

    fun formatNoteDate(dateTime: LocalDateTime): String {
        val month = dateTime.month.name.lowercase().take(3).replaceFirstChar { it.uppercase() }
        val day = if (dateTime.dayOfMonth < 10) "0${dateTime.dayOfMonth}" else dateTime.dayOfMonth
        val year = dateTime.year
        val hour = if (dateTime.hour < 10) "0${dateTime.hour}" else dateTime.hour
        val minute = if (dateTime.minute < 10) "0${dateTime.minute}" else dateTime.minute

        return buildString {
            append(month)
            append(" ")
            append(day)
            append(" ")
            append(year)
            append(", ")
            append(hour)
            append(":")
            append(minute)
        }
    }

}