package ru.blizzed.timetablespbu.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.subjects.BehaviorSubject
import ru.blizzed.timetablespbu.domain.entities.EducatorEntity
import ru.blizzed.timetablespbu.domain.entities.Faculty
import ru.blizzed.timetablespbu.domain.repositories.EducatorsRepository
import ru.blizzed.timetablespbu.domain.repositories.FacultiesRepository
import ru.blizzed.timetablespbu.utils.Event
import ru.blizzed.timetablespbu.utils.RxBinder
import ru.blizzed.timetablespbu.viewmodel.system.RxViewModel
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class EducatorSearchViewModel @Inject constructor(
    rxBinder: RxBinder,
    private val educatorsRepository: EducatorsRepository,
    private val facultiesRepository: FacultiesRepository
) : RxViewModel(rxBinder) {

    companion object {
        private const val SEARCH_QUERY_TIMEOUT = 250L
    }

    val educatorsSearchProcess: LiveData<Event<List<EducatorEntity>>> = MutableLiveData()
    val classroomsSearchProcess: LiveData<Event<List<Faculty>>> = MutableLiveData()

    private val searchQuerySubject = BehaviorSubject.createDefault("")

    init {
        searchQuerySubject
            .debounce(SEARCH_QUERY_TIMEOUT, TimeUnit.MILLISECONDS)
            .filter { it.length > 1 }
            .doOnNext { (educatorsSearchProcess as MutableLiveData).postValue(Event.Loading()) }
            .flatMapSingle(educatorsRepository::search)
            .bindTo(educatorsSearchProcess)

        facultiesRepository.getFaculties().bindTo(classroomsSearchProcess)
    }

    fun setSearchQuery(query: String) = searchQuerySubject.onNext(query)

}