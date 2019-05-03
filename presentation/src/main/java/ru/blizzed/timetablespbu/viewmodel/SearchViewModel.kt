package ru.blizzed.timetablespbu.viewmodel

import androidx.lifecycle.LiveData
import io.reactivex.subjects.BehaviorSubject
import ru.blizzed.timetablespbu.extensions.toLiveData
import ru.blizzed.timetablespbu.utils.RxBinder
import ru.blizzed.timetablespbu.viewmodel.system.RxViewModel
import java.util.concurrent.TimeUnit

open class SearchViewModel(
        rxBinder: RxBinder,
        queryDebounceMillis: Int = SEARCH_QUERY_DEBOUNCE_MILLIS
) : RxViewModel(rxBinder) {

    companion object {
        private const val SEARCH_QUERY_DEBOUNCE_MILLIS = 300
    }

    val searchQueryLiveData: LiveData<String>

    protected val searchQuerySubject = BehaviorSubject.createDefault("")
    protected val searchQueryObservable = searchQuerySubject.debounce(queryDebounceMillis.toLong(), TimeUnit.MILLISECONDS)

    init {
        searchQueryLiveData = searchQuerySubject.toLiveData()
    }

    open fun setSearchQuery(query: String) = searchQuerySubject.onNext(query)

    open fun getSearchQuery() = searchQuerySubject.value.orEmpty()

}