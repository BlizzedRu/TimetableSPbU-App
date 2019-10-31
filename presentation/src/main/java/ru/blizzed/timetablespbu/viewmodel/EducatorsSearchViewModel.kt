package ru.blizzed.timetablespbu.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.blizzed.timetablespbu.domain.entities.Educator
import ru.blizzed.timetablespbu.domain.repositories.EducatorsRepository
import ru.blizzed.timetablespbu.utils.Event
import ru.blizzed.timetablespbu.utils.RxBinder
import javax.inject.Inject

class EducatorsSearchViewModel @Inject constructor(
        rxBinder: RxBinder,
        private val educatorsRepository: EducatorsRepository
) : SearchViewModel(rxBinder) {

    val educatorsSearchProcess: LiveData<Event<List<Educator>>> = MutableLiveData()
    val educatorSetViewedProcess: LiveData<Event<Educator>> = MutableLiveData()
    val educatorSetFavoriteProcess: LiveData<Event<Educator>> = MutableLiveData()

    init {
        searchQueryObservable
                .doOnNext { query ->
                    if (query.isNotBlank()) educatorsSearchProcess.postLoadingEvent(educatorsSearchProcess.value?.data)
                }
                .switchMapSingle(educatorsRepository::search)
                .bindTo(educatorsSearchProcess)
    }

    fun setViewed(educator: Educator) {
        educatorsRepository.setViewed(educator).bindTo(educatorSetViewedProcess)
    }

    fun setFavorite(educator: Educator, isFavorite: Boolean) {
        educatorsRepository.setFavorite(educator, isFavorite).bindTo(educatorSetFavoriteProcess)
    }

}
