package ru.blizzed.timetablespbu.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.blizzed.timetablespbu.ui.screens.common.faculty_search.FacultiesSearchViewModel
import ru.blizzed.timetablespbu.ui.screens.common.group_search.AdmissionYearStepViewModel
import ru.blizzed.timetablespbu.ui.screens.common.group_search.FacultyStepViewModel
import ru.blizzed.timetablespbu.ui.screens.common.group_search.StudyLevelStepViewModel
import ru.blizzed.timetablespbu.ui.screens.common.group_search.base.SelectionStepsSharedViewModel
import ru.blizzed.timetablespbu.ui.screens.common.group_search.legacy.GroupSearchViewModel
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
    viewModel { GroupSearchViewModel(get()) }
    viewModel { SelectionStepsSharedViewModel() }
    viewModel { FacultyStepViewModel(get()) }
    viewModel { StudyLevelStepViewModel(get()) }
    viewModel { AdmissionYearStepViewModel(get()) }
}
