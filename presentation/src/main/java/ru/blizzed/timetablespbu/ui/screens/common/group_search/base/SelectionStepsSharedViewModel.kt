package ru.blizzed.timetablespbu.ui.screens.common.group_search.base

import androidx.lifecycle.ViewModel

class SelectionStepsSharedViewModel: ViewModel() {

    fun dispatchEvent(event: SharedEvent<*>) {

    }

}

sealed class SharedEvent<T>(open val selectedItem: T) {
    class ItemSelected<T>(override val selectedItem: T) : SharedEvent<T>(selectedItem)
}
