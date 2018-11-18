package ru.blizzed.timetablespbu.di.components

import dagger.Component
import ru.blizzed.timetablespbu.TimetableSPbUApp
import ru.blizzed.timetablespbu.di.modules.InteractorsModule
import ru.blizzed.timetablespbu.di.modules.PersistenceModule
import ru.blizzed.timetablespbu.di.modules.RepositoriesModule
import ru.blizzed.timetablespbu.di.modules.TimetableApiModule
import ru.blizzed.timetablespbu.di.scopes.Screens
import ru.blizzed.timetablespbu.di.viewmodel.ViewModelsModule

@Screens
@Component(
    modules = [
        ViewModelsModule::class,
        InteractorsModule::class,
        RepositoriesModule::class,
        PersistenceModule::class,
        TimetableApiModule::class
    ], dependencies = [AppComponent::class]
)
interface ScreensComponent {

    fun inject(into: TimetableSPbUApp)

}