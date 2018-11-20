package ru.blizzed.timetablespbu.di.viewmodel

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.blizzed.timetablespbu.viewmodel.SearchViewModel

@Module
interface ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelLink(SearchViewModel::class)
    fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel

}