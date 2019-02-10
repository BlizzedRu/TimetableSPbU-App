package ru.blizzed.timetablespbu.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.blizzed.timetablespbu.data.utils.AssetsUtil
import ru.blizzed.timetablespbu.di.scopes.App

@Module
class UtilsModule {

    @App
    @Provides
    fun provideAssetsUtil(context: Context) = AssetsUtil(context)

}