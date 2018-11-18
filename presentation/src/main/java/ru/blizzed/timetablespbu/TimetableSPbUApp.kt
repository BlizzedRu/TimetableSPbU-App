package ru.blizzed.timetablespbu

import android.app.Application
import ru.blizzed.timetablespbu.di.components.DaggerAppComponent
import ru.blizzed.timetablespbu.di.components.DaggerScreensComponent
import ru.blizzed.timetablespbu.di.components.ScreensComponent
import ru.blizzed.timetablespbu.di.modules.AppModule
import ru.blizzed.timetablespbu.viewmodel.ViewModelFactory
import ru.blizzed.timetablespbu.viewmodel.ViewModelFactoryProvider
import timber.log.Timber
import javax.inject.Inject

class TimetableSPbUApp : Application(), ViewModelFactoryProvider {

    companion object {
        private lateinit var instance: TimetableSPbUApp
        fun screensComponent() = instance.screensComponent
    }

    @Inject
    override lateinit var viewModelFactory: ViewModelFactory

    private lateinit var screensComponent: ScreensComponent

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()

        screensComponent = DaggerScreensComponent.builder()
            .appComponent(
                DaggerAppComponent.builder()
                    .appModule(AppModule(this))
                    .build()
            )
            .build()
            .apply { inject(this@TimetableSPbUApp) }

        Timber.plant(Timber.DebugTree())
    }

}