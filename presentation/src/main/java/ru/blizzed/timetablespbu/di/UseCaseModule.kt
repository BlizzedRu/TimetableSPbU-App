package ru.blizzed.timetablespbu.di

import org.koin.dsl.module
import ru.blizzed.timetablespbu.domain.usecases.GetFacultiesUseCase
import ru.blizzed.timetablespbu.domain.usecases.UserInfoUseCase
import ru.blizzed.timetablespbu.domain.usecases.group_selection.GetGroupsByProgramUseCase
import ru.blizzed.timetablespbu.domain.usecases.group_selection.GetProgramCombinationsUseCase
import ru.blizzed.timetablespbu.domain.usecases.group_selection.GetStudyLevelsUseCase
import ru.blizzed.timetablespbu.domain.usecases.group_selection.ObserveSelectedGroupsUseCase
import ru.blizzed.timetablespbu.domain.usecases.group_selection.SelectGroupUseCase

val useCaseModule = module {
  single { UserInfoUseCase(get()) }
  single { GetFacultiesUseCase(get()) }
  single { GetStudyLevelsUseCase(get()) }
  single { GetProgramCombinationsUseCase(get()) }
  single { GetGroupsByProgramUseCase(get()) }
  single { SelectGroupUseCase(get()) }
  single { ObserveSelectedGroupsUseCase(get()) }
}
