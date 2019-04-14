package ru.blizzed.timetablespbu.di.viewmodel

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.blizzed.timetablespbu.viewmodel.EducatorSearchViewModel
import ru.blizzed.timetablespbu.viewmodel.FacultiesViewModel

@Module
interface ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelLink(EducatorSearchViewModel::class)
    fun bindSearchViewModel(searchViewModel: EducatorSearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelLink(FacultiesViewModel::class)
    fun bindFacultiesViewModel(searchViewModel: FacultiesViewModel): ViewModel

}
