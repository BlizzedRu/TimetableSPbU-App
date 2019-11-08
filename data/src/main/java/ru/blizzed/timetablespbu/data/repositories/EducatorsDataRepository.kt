package ru.blizzed.timetablespbu.data.repositories

import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import ru.blizzed.timetablespbu.data.datasources.EducatorsLocalDataSource
import ru.blizzed.timetablespbu.data.datasources.EducatorsRemoteDataSource
import ru.blizzed.timetablespbu.data.utils.TimeUtils
import ru.blizzed.timetablespbu.domain.entities.Educator
import ru.blizzed.timetablespbu.domain.repositories.EducatorsRepository

class EducatorsDataRepository(
        private val localDataSource: EducatorsLocalDataSource,
        private val remoteDataSource: EducatorsRemoteDataSource,
        private val timeUtils: TimeUtils
) : EducatorsRepository {

    companion object {
        private const val MIN_QUERY_LENGTH_FOR_REMOTE_REQUEST = 2
    }

    override fun observeFavorites(): Flowable<List<Educator>> = localDataSource.observeFavorites()

    override fun observeNonFavoritesViewed(): Flowable<List<Educator>> = localDataSource.observeNonFavoriteViewed()

    override fun search(query: String): Single<List<Educator>> = Single.zip(
            if (query.length >= MIN_QUERY_LENGTH_FOR_REMOTE_REQUEST) remoteDataSource.search(query) else Single.just(emptyList()),
            localDataSource.getAll(query),
            BiFunction { remoteList, localList ->
                val sortedLocalList = localList.sortedByDescending(Educator::lastInteractionTime)

                // TODO may be updateOrAdd cached educators

                sortedLocalList + remoteList.toMutableList().also { it.removeAll(sortedLocalList) }
            }
    )

    override fun isFavorite(educator: Educator): Single<Boolean> = localDataSource.isFavorite(educator)

    override fun getFavorites(): Maybe<List<Educator>> = localDataSource.getFavorites()

    override fun setFavorite(educator: Educator, isFavorite: Boolean): Single<Educator> = localDataSource
            .run {
                if (isFavorite || educator.isViewed) {
                    updateOrAdd(educator.also { it.setFavorite(isFavorite, timeUtils.getCurrentTime()) })
                } else {
                    delete(educator)
                }
            }.andThen(Single.just(educator))

    override fun getViewed(): Maybe<List<Educator>> = localDataSource.getViewed()

    override fun setViewed(educator: Educator): Single<Educator> = localDataSource
            .updateOrAdd(educator.also { it.view(timeUtils.getCurrentTime()) })
            .andThen(Single.just(educator))

}
