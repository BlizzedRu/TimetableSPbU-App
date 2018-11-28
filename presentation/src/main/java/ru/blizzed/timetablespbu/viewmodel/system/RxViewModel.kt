package ru.blizzed.timetablespbu.viewmodel.system

import androidx.lifecycle.ViewModel
import ru.blizzed.timetablespbu.utils.RxBinder

open class RxViewModel(private val rxBinder: RxBinder) : ViewModel(), RxBinder by rxBinder {

    override fun onCleared() {
        super.onCleared()
        rxBinder.clearSubscriptions()
    }

}