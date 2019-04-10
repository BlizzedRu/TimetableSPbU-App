package ru.blizzed.timetablespbu.viewmodel.system

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.blizzed.timetablespbu.utils.Event

open class BaseViewModel : ViewModel() {

    protected fun <T> LiveData<Event<T>>.postLoadingEvent(data: T? = null) =
        postEvent(Event.Loading(data))

    protected fun <T> LiveData<Event<T>>.postErrorEvent(throwable: Throwable, data: T? = null) =
        postEvent(Event.Error(throwable, data))

    protected fun <T> LiveData<Event<T>>.postSuccessEvent(data: T) =
        postEvent(Event.Success(data))

    protected fun <T> LiveData<Event<T>>.postCompleteEvent(data: T? = null) =
        postEvent(Event.Complete(data))

    private fun <T> LiveData<Event<T>>.postEvent(event: Event<T>) =
        (this as MutableLiveData).postValue(event)

}