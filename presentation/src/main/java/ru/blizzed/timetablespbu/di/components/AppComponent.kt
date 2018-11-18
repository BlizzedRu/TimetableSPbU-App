package ru.blizzed.timetablespbu.di.components

import android.content.Context
import dagger.Component
import ru.blizzed.timetablespbu.di.modules.AppModule
import ru.blizzed.timetablespbu.di.modules.UtilsModule
import ru.blizzed.timetablespbu.di.scopes.App

@App
@Component(modules = [AppModule::class, UtilsModule::class])
interface AppComponent {

    fun context(): Context

}