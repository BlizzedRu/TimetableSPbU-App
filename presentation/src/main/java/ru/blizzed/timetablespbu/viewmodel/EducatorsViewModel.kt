package ru.blizzed.timetablespbu.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Flowable
import io.reactivex.functions.BiFunction
import ru.blizzed.timetablespbu.domain.entities.Educator
import ru.blizzed.timetablespbu.domain.repositories.EducatorsRepository
import ru.blizzed.timetablespbu.utils.Event
import ru.blizzed.timetablespbu.utils.RxBinder
import ru.blizzed.timetablespbu.viewmodel.system.RxViewModel
import javax.inject.Inject

class EducatorsViewModel @Inject constructor(
        rxBinder: RxBinder,
        private val educatorsRepository: EducatorsRepository
) : RxViewModel(rxBinder) {

    val educatorsLoadingProcess: LiveData<Event<Educators>> = MutableLiveData()

    init {
        Flowable.zip(
                educatorsRepository.observeFavorites(),
                educatorsRepository.observeNonFavorites(),
                BiFunction { favoriteList: List<Educator>, viewedList: List<Educator> ->
                    Educators(favoriteList, viewedList)
                }
        ).bindTo(educatorsLoadingProcess)
    }

}

data class Educators(val favorites: List<Educator>, val viewed: List<Educator>)
