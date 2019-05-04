package ru.blizzed.timetablespbu.domain.repositories

import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import ru.blizzed.timetablespbu.domain.entities.Educator

interface EducatorsRepository {

    fun observeFavorites(): Flowable<List<Educator>>

    fun observeNonFavoritesViewed(): Flowable<List<Educator>>

    fun search(query: String): Single<List<Educator>>

    fun isFavorite(educator: Educator): Single<Boolean>

    fun getFavorites(): Maybe<List<Educator>>

    fun setFavorite(educator: Educator, isFavorite: Boolean): Single<Educator>

    fun getViewed(): Maybe<List<Educator>>

    fun setViewed(educator: Educator): Single<Educator>

}
