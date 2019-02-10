package ru.blizzed.timetablespbu.di.components

import dagger.Component
import ru.blizzed.timetablespbu.TimetableSPbUApp
import ru.blizzed.timetablespbu.di.modules.*
import ru.blizzed.timetablespbu.di.scopes.App
import ru.blizzed.timetablespbu.di.viewmodel.ViewModelsModule

@App
@Component(
    modules = [
        AppModule::class,
        ViewModelsModule::class,
        RepositoriesModule::class,
        UtilsModule::class,
        RxModule::class,
        PersistenceModule::class,
        TimetableApiModule::class
    ]
)
interface AppComponent {

    fun inject(into: TimetableSPbUApp)

}