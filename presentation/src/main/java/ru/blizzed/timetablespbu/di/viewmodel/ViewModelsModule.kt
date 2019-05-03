package ru.blizzed.timetablespbu.di.viewmodel

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.blizzed.timetablespbu.viewmodel.EducatorsSearchViewModel
import ru.blizzed.timetablespbu.viewmodel.EducatorsViewModel
import ru.blizzed.timetablespbu.viewmodel.FacultiesViewModel

@Module
interface ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelLink(EducatorsSearchViewModel::class)
    fun bindEducatorsSearchViewModel(viewModel: EducatorsSearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelLink(FacultiesViewModel::class)
    fun bindFacultiesViewModel(viewModel: FacultiesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelLink(EducatorsViewModel::class)
    fun bindEducatorsViewModel(viewModel: EducatorsViewModel): ViewModel

}
