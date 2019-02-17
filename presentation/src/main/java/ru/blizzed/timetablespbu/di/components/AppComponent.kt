package ru.blizzed.timetablespbu.di.components

import dagger.Component
import ru.blizzed.timetablespbu.TimetableSPbUApp
import ru.blizzed.timetablespbu.di.modules.AppModule
import ru.blizzed.timetablespbu.di.modules.PersistenceModule
import ru.blizzed.timetablespbu.di.modules.RepositoriesModule
import ru.blizzed.timetablespbu.di.modules.RxModule
import ru.blizzed.timetablespbu.di.modules.TimetableApiModule
import ru.blizzed.timetablespbu.di.scopes.App
import ru.blizzed.timetablespbu.di.viewmodel.ViewModelsModule

@App
@Component(
    modules = [
        AppModule::class,
        ViewModelsModule::class,
        RepositoriesModule::class,
        RxModule::class,
        PersistenceModule::class,
        TimetableApiModule::class
    ]
)
interface AppComponent {

    fun inject(into: TimetableSPbUApp)

}