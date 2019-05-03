package ru.blizzed.timetablespbu.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.data.utils.AssetsUtil
import ru.blizzed.timetablespbu.data.utils.ColorGenerator
import ru.blizzed.timetablespbu.data.utils.TimeUtils
import ru.blizzed.timetablespbu.di.scopes.App

@Module
class UtilsModule {

    @App
    @Provides
    fun provideAssetsUtil(context: Context): AssetsUtil = AssetsUtil(context)

    @App
    @Provides
    fun provideTimeUtils(): TimeUtils = TimeUtils

    @App
    @Provides
    fun provideColorGenerator(context: Context): ColorGenerator = ColorGenerator(context.resources.getColor(R.color.primary_light))

}
