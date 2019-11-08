package ru.blizzed.timetablespbu.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.blizzed.timetablespbu.ui.screens.search.faculties.FacultiesSearchViewModel
import ru.blizzed.timetablespbu.viewmodel.EducatorsSearchViewModel
import ru.blizzed.timetablespbu.viewmodel.EducatorsViewModel
import ru.blizzed.timetablespbu.viewmodel.FacultiesViewModel

val viewModelModule = module {
    viewModel { EducatorsSearchViewModel(get(), get()) }
    viewModel { EducatorsViewModel(get(), get()) }
    viewModel { FacultiesViewModel(get(), get()) }
    viewModel { FacultiesSearchViewModel(get()) }
}
