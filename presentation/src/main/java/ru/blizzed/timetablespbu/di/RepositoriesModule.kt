package ru.blizzed.timetablespbu.di

import org.koin.dsl.module
import ru.blizzed.timetablespbu.data.repositories.EducatorsDataRepository
import ru.blizzed.timetablespbu.data.repositories.FacultiesDataRepository
import ru.blizzed.timetablespbu.domain.repositories.EducatorsRepository
import ru.blizzed.timetablespbu.domain.repositories.FacultiesRepository

val repositoriesModule = module {
    single<EducatorsRepository> { EducatorsDataRepository(get(), get(), get()) }
    single<FacultiesRepository> { FacultiesDataRepository(get()) }
}
