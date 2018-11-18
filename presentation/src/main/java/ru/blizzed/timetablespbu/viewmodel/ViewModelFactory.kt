package ru.blizzed.timetablespbu.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.blizzed.timetablespbu.di.scopes.Screens
import javax.inject.Inject
import javax.inject.Provider

@Screens
class ViewModelFactory @Inject constructor(
        private val viewModelsMap: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(viewModelClass: Class<T>): T =
            viewModelsMap[viewModelClass]?.get() as T
                ?: throw IllegalArgumentException("ViewModel class ${viewModelClass.canonicalName} not found")

}