package ru.blizzed.timetablespbu.di.components

import dagger.Component
import ru.blizzed.timetablespbu.TimetableSPbUApp
import ru.blizzed.timetablespbu.di.modules.AppModule
import ru.blizzed.timetablespbu.di.modules.PersistenceModule
import ru.blizzed.timetablespbu.di.modules.RepositoriesModule
import ru.blizzed.timetablespbu.di.modules.RxModule
import ru.blizzed.timetablespbu.di.modules.TimetableApiModule
import ru.blizzed.timetablespbu.di.modules.UtilsModule
import ru.blizzed.timetablespbu.di.scopes.App
import ru.blizzed.timetablespbu.di.viewmodel.ViewModelsModule
import ru.blizzed.timetablespbu.ui.screens.faculties.FacultiesSearchFragment

@App
@Component(modules = [
    AppModule::class,
    ViewModelsModule::class,
    RepositoriesModule::class,
    RxModule::class,
    PersistenceModule::class,
    TimetableApiModule::class,
    UtilsModule::class
])
interface AppComponent {

    fun inject(into: TimetableSPbUApp)

    fun inject(into: FacultiesSearchFragment)

}
