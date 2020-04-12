package ru.blizzed.timetablespbu.di

import org.koin.dsl.module
import ru.blizzed.timetablespbu.domain.usecases.GetFacultiesUseCase
import ru.blizzed.timetablespbu.domain.usecases.UserInfoUseCase
import ru.blizzed.timetablespbu.domain.usecases.timetable_browse.AddGroupUseCase
import ru.blizzed.timetablespbu.domain.usecases.timetable_browse.GetGroupsByProgramUseCase
import ru.blizzed.timetablespbu.domain.usecases.timetable_browse.GetProgramCombinationsUseCase
import ru.blizzed.timetablespbu.domain.usecases.timetable_browse.GetStudyLevelsUseCase
import ru.blizzed.timetablespbu.domain.usecases.timetable_browse.ObserveSelectedGroupsUseCase
import ru.blizzed.timetablespbu.domain.usecases.timetable_browse.RemoveGroupUseCase
import ru.blizzed.timetablespbu.domain.usecases.timetable_browse.SelectGroupUseCase

val useCaseModule = module {
  single { UserInfoUseCase(get()) }
  single { GetFacultiesUseCase(get()) }
  single { GetStudyLevelsUseCase(get()) }
  single { GetProgramCombinationsUseCase(get()) }
  single { GetGroupsByProgramUseCase(get()) }
  single { SelectGroupUseCase(get(), get(), get()) }
  single { RemoveGroupUseCase(get()) }
  single { AddGroupUseCase(get()) }
  single { ObserveSelectedGroupsUseCase(get()) }
}
