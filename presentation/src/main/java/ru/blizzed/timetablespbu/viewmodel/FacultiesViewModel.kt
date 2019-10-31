package ru.blizzed.timetablespbu.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.blizzed.timetablespbu.domain.entities.Faculty
import ru.blizzed.timetablespbu.domain.repositories.FacultiesRepository
import ru.blizzed.timetablespbu.utils.Event
import ru.blizzed.timetablespbu.utils.RxBinder
import javax.inject.Inject

class FacultiesViewModel @Inject constructor(
        rxBinder: RxBinder,
        facultiesRepository: FacultiesRepository
) : SearchViewModel(
        queryDebounceMillis = 100,
        rxBinder = rxBinder
) {

    val faculties: LiveData<Event<List<Faculty>>> = MutableLiveData()

    init {
        searchQueryObservable.switchMapSingle {
            facultiesRepository.getFaculties()
        }.map { faculties ->
            faculties.filter { faculty ->
                faculty.name.contains(getSearchQuery(), true)
            }
        }.bindTo(faculties)
    }

}