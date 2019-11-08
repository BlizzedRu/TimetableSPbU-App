package ru.blizzed.timetablespbu.di.koin

import android.preference.PreferenceManager
import androidx.arch.core.executor.ArchTaskExecutor
import androidx.room.Room
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.data.persistance.TTDatabase
import ru.blizzed.timetablespbu.data.utils.AssetsUtil
import ru.blizzed.timetablespbu.data.utils.ColorGenerator
import ru.blizzed.timetablespbu.data.utils.TimeUtils
import ru.blizzed.timetablespbu.extensions.getColorCompat
import ru.blizzed.timetablespbu.utils.BaseRxBinder
import ru.blizzed.timetablespbulib.TimeTableApi

val applicationModule = module {
    single { PreferenceManager.getDefaultSharedPreferences(androidContext()) }
}

val dbScheduler = named("DBScheduler")

val persistenceModule = module {

    single {
        Room.databaseBuilder(androidContext(), TTDatabase::class.java, "database")
                .fallbackToDestructiveMigration()
                .build()
    }

    single(dbScheduler) { Schedulers.from(ArchTaskExecutor.getIOThreadExecutor()) }

    single { get<TTDatabase>().addressesDao }
    single { get<TTDatabase>().educatorsDao }
    single { get<TTDatabase>().classroomsDao }

}

val ioScheduler = named("IOScheduler")

val uiScheduler = named("UIScheduler")

val rxModule = module {
    single(ioScheduler) { Schedulers.io() }
    single(uiScheduler) { AndroidSchedulers.mainThread() }
    factory { BaseRxBinder(get(uiScheduler), get(ioScheduler)) }
}

val timeTableApiModule = module {
    single { TimeTableApi.addresses() }
    single { TimeTableApi.educators() }
    single { TimeTableApi.extracurDivisions() }
    single { TimeTableApi.groups() }
    single { TimeTableApi.divisions() }
}

val utilsModule = module {
    single { AssetsUtil(androidContext()) }
    single { TimeUtils }
    single { ColorGenerator(androidContext().getColorCompat(R.color.primary_light)) }
}
