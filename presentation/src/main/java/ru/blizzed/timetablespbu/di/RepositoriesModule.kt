package ru.blizzed.timetablespbu.di

import org.koin.dsl.module
import ru.blizzed.timetablespbu.data.repositories.EducatorsDataRepository
import ru.blizzed.timetablespbu.data.repositories.FacultiesDataRepository
import ru.blizzed.timetablespbu.data.repositories.GroupSelectionDataRepository
import ru.blizzed.timetablespbu.data.repositories.TimetableBrowseDataRepository
import ru.blizzed.timetablespbu.data.repositories.UserInfoDataRepository
import ru.blizzed.timetablespbu.domain.repositories.EducatorsRepository
import ru.blizzed.timetablespbu.domain.repositories.FacultiesRepository
import ru.blizzed.timetablespbu.domain.repositories.GroupSelectionRepository
import ru.blizzed.timetablespbu.domain.repositories.TimetableBrowseRepository
import ru.blizzed.timetablespbu.domain.repositories.UserInfoRepository

val repositoriesModule = module {
    single<UserInfoRepository> { UserInfoDataRepository(get()) }
    single<EducatorsRepository> { EducatorsDataRepository(get(), get(), get()) }
    single<FacultiesRepository> { FacultiesDataRepository(get()) }
    single<TimetableBrowseRepository> { TimetableBrowseDataRepository(get(), get(), get()) }
    single<GroupSelectionRepository> { GroupSelectionDataRepository(get()) }
}
