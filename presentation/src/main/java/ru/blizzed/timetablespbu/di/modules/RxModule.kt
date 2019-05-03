package ru.blizzed.timetablespbu.di.modules

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.blizzed.timetablespbu.di.qualifiers.IOScheduler
import ru.blizzed.timetablespbu.di.qualifiers.UIScheduler
import ru.blizzed.timetablespbu.di.scopes.App
import ru.blizzed.timetablespbu.utils.BaseRxBinder
import ru.blizzed.timetablespbu.utils.RxBinder

@Module
class RxModule {

    @App
    @IOScheduler
    @Provides
    fun provideIoScheduler() = Schedulers.io()

    @App
    @UIScheduler
    @Provides
    fun provideUiScheduler() = AndroidSchedulers.mainThread()

    @Provides
    fun provideRxBinder(
        @UIScheduler uiScheduler: Scheduler,
        @IOScheduler ioScheduler: Scheduler
    ): RxBinder = BaseRxBinder(uiScheduler, ioScheduler)

}
