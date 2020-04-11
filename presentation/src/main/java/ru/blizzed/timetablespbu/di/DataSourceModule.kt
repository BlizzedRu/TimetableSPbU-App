package ru.blizzed.timetablespbu.di

import org.koin.dsl.module
import ru.blizzed.timetablespbu.data.datasources.AddressesLocalDataSource
import ru.blizzed.timetablespbu.data.datasources.AddressesRemoteDataSource
import ru.blizzed.timetablespbu.data.datasources.DivisionsLocalDataSource
import ru.blizzed.timetablespbu.data.datasources.DivisionsRemoteDataSource
import ru.blizzed.timetablespbu.data.datasources.EducatorsLocalDataSource
import ru.blizzed.timetablespbu.data.datasources.EducatorsRemoteDataSource
import ru.blizzed.timetablespbu.data.datasources.FacultiesAssetsDataSource
import ru.blizzed.timetablespbu.data.datasources.UserInfoDataSource

val dataSourceModule = module {
    single { AddressesLocalDataSource(get(), get(dbScheduler)) }
    single { AddressesRemoteDataSource(get()) }

    single { EducatorsLocalDataSource(get(), get(dbScheduler)) }
    single { EducatorsRemoteDataSource(get()) }

    single { FacultiesAssetsDataSource(get(), get(ioScheduler)) }

    single { UserInfoDataSource(get(), get(dbScheduler)) }

    single { DivisionsRemoteDataSource(get(), get(ioScheduler)) }
    single { DivisionsLocalDataSource() }
}
