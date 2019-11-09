package ru.blizzed.timetablespbu.di

import org.koin.dsl.module
import ru.blizzed.timetablespbu.domain.usecases.UserInfoUseCase

val useCaseModule = module {
    single { UserInfoUseCase(get()) }
}
