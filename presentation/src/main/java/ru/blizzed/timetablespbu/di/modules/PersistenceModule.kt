package ru.blizzed.timetablespbu.di.modules

import android.annotation.SuppressLint
import android.content.Context
import androidx.arch.core.executor.ArchTaskExecutor
import androidx.room.Room
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import ru.blizzed.timetablespbu.data.DBScheduler
import ru.blizzed.timetablespbu.data.persistance.TTDatabase
import ru.blizzed.timetablespbu.data.persistance.dao.EducatorsDao
import ru.blizzed.timetablespbu.di.scopes.App

@Module
class PersistenceModule {

    @SuppressLint("RestrictedApi")
    @App
    @DBScheduler
    @Provides
    fun provideDbScheduler() = Schedulers.from(ArchTaskExecutor.getIOThreadExecutor())

    @App
    @Provides
    fun provideDatabase(context: Context): TTDatabase = Room
            .databaseBuilder(context, TTDatabase::class.java, "database")
            .fallbackToDestructiveMigration()
            .build()

    @App
    @Provides
    fun provideEducatorsDao(database: TTDatabase): EducatorsDao = database.educatorsDao

}
