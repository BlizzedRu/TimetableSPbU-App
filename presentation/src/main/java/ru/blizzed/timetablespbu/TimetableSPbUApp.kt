package ru.blizzed.timetablespbu

import android.app.Application
import ru.blizzed.timetablespbu.di.components.AppComponent
import ru.blizzed.timetablespbu.di.components.DaggerAppComponent
import ru.blizzed.timetablespbu.di.modules.AppModule
import ru.blizzed.timetablespbu.viewmodel.system.ViewModelFactory
import ru.blizzed.timetablespbu.viewmodel.system.ViewModelFactoryProvider
import timber.log.Timber
import javax.inject.Inject

class TimetableSPbUApp : Application(), ViewModelFactoryProvider {

    companion object {
        private lateinit var instance: TimetableSPbUApp
        fun appComponent() = instance.appComponent
    }

    @Inject
    override lateinit var viewModelFactory: ViewModelFactory

    private lateinit var appComponent: AppComponent

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
            .apply { inject(this@TimetableSPbUApp) }

        Timber.plant(Timber.DebugTree())
    }

}