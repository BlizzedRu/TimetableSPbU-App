package ru.blizzed.timetablespbu.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.subjects.BehaviorSubject
import ru.blizzed.timetablespbu.data.repositories.EducatorsSearchRepository
import ru.blizzed.timetablespbu.domain.entities.EducatorEntity
import ru.blizzed.timetablespbu.utils.Event
import ru.blizzed.timetablespbu.utils.RxBinder
import ru.blizzed.timetablespbu.viewmodel.system.RxViewModel
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    rxBinder: RxBinder,
    private val searchRepository: EducatorsSearchRepository
) : RxViewModel(rxBinder) {

    companion object {
        private const val SEARCH_QUERY_TIMEOUT = 250L
    }

    val searchProcess: LiveData<Event<List<EducatorEntity>>> = MutableLiveData()

    private val searchQuerySubject = BehaviorSubject.createDefault("")

    init {
        searchQuerySubject
            .debounce(SEARCH_QUERY_TIMEOUT, TimeUnit.MILLISECONDS)
            .filter { it.length > 1 }
            .flatMapSingle(searchRepository::search)
            .bindTo(searchProcess)
    }

    fun setSearchQuery(query: String) = searchQuerySubject.onNext(query)

}