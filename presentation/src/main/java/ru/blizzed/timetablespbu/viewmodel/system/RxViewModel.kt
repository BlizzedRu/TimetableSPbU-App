package ru.blizzed.timetablespbu.viewmodel.system

import ru.blizzed.timetablespbu.utils.RxBinder

open class RxViewModel(private val rxBinder: RxBinder) : BaseViewModel(), RxBinder by rxBinder {

    override fun onCleared() {
        super.onCleared()
        rxBinder.clearSubscriptions()
    }

}