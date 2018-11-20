package ru.blizzed.timetablespbu.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber
import javax.inject.Inject

class SearchViewModel @Inject constructor() : ViewModel() {

    private val searchQuerySubject = BehaviorSubject.createDefault("")

    init {
        searchQuerySubject.subscribe {
            Timber.wtf(it)
        }
    }

    fun setSearchQuery(query: String) = searchQuerySubject.onNext(query)

}