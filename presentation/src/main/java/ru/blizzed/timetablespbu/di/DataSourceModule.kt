package ru.blizzed.timetablespbu.di

import org.koin.dsl.module
import ru.blizzed.timetablespbu.data.datasources.*

val dataSourceModule = module {
    single { AddressesLocalDataSource(get(), get(dbScheduler)) }
    single { AddressesRemoteDataSource(get()) }

    single { EducatorsLocalDataSource(get(), get(dbScheduler)) }
    single { EducatorsRemoteDataSource(get()) }

    single { FacultiesAssetsDataSource(get(), get(ioScheduler)) }
}
