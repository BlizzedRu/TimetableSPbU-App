package ru.blizzed.timetablespbu.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.blizzed.timetablespbu.ui.screens.common.faculty_selection.FacultiesSearchViewModel
import ru.blizzed.timetablespbu.ui.screens.launcher.LauncherViewModel
import ru.blizzed.timetablespbu.ui.screens.welcome.student.GroupSelectionSharedViewModel
import ru.blizzed.timetablespbu.viewmodel.EducatorsSearchViewModel
import ru.blizzed.timetablespbu.viewmodel.EducatorsViewModel
import ru.blizzed.timetablespbu.viewmodel.FacultiesViewModel

val viewModelModule = module {
    viewModel { LauncherViewModel(get()) }
    viewModel { EducatorsSearchViewModel(get(), get()) }
    viewModel { EducatorsViewModel(get(), get()) }
    viewModel { FacultiesViewModel(get(), get()) }
    viewModel { FacultiesSearchViewModel(get()) }
    viewModel { GroupSelectionSharedViewModel() }
}
