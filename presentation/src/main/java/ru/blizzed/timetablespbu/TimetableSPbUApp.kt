package ru.blizzed.timetablespbu

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.blizzed.timetablespbu.di.*
import timber.log.Timber

class TimetableSPbUApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@TimetableSPbUApp)
            modules(
                    listOf(
                            applicationModule,
                            persistenceModule,
                            rxModule,
                            timeTableApiModule,
                            utilsModule,
                            dataSourceModule,
                            repositoriesModule,
                            viewModelModule,
                            useCaseModule
                    )
            )
        }

        Timber.plant(Timber.DebugTree())
    }

}
