package classes

// src/main/kotlin/classes/MyApplication.kt
import android.app.Application
import android.content.Context
import di.databaseModule
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import viewmodel.UsersViewModel

class MyApplication : Application() {

    private val usersViewModel: UsersViewModel by lazy {
        UsersViewModel(get())
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        startKoin {
            androidContext(this@MyApplication)
            modules(databaseModule, module {
                single { usersViewModel }
            })
        }
    }

    companion object {
        var context: Context? = null
    }

//        startKoin {
//            androidContext(this@MyApplication)
//            modules(
//                databaseModule,
//                module {
//                    single { UsersRepositoryImp(get()) }
//                    viewModel { UsersViewModel(get()) }
//                }
//            )
//        }
}

