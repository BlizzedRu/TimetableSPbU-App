package ru.blizzed.timetablespbu.di.modules

import android.annotation.SuppressLint
import androidx.arch.core.executor.ArchTaskExecutor
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import ru.blizzed.timetablespbu.di.qualifiers.DBScheduler

@Module
class PersistenceModule {

    @SuppressLint("RestrictedApi")
    @DBScheduler
    @Provides
    fun provideDbScheduler() = Schedulers.from(ArchTaskExecutor.getIOThreadExecutor())

}