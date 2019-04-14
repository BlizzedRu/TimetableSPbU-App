package ru.blizzed.timetablespbu.data.repositories

import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.functions.Function3
import ru.blizzed.timetablespbu.data.datasources.EducatorsLocalDataSource
import ru.blizzed.timetablespbu.data.datasources.EducatorsRemoteDataSource
import ru.blizzed.timetablespbu.data.utils.TimeUtils
import ru.blizzed.timetablespbu.domain.entities.Educator
import ru.blizzed.timetablespbu.domain.repositories.EducatorsRepository
import javax.inject.Inject

class EducatorsDataRepository @Inject constructor(
        private val localDataSource: EducatorsLocalDataSource,
        private val remoteDataSource: EducatorsRemoteDataSource,
        private val timeUtils: TimeUtils
) : EducatorsRepository {

    override fun search(query: String): Single<List<Educator>> = Single.zip(
            remoteDataSource.search(query),
            localDataSource.getFavorites().toSingle(emptyList()),
            localDataSource.getViewed().toSingle(emptyList()),
            Function3 { remoteList, favoritesList, viewedList ->
                val localList = (favoritesList + viewedList)
                        .filter { it.fullName.contains(query, ignoreCase = true) }
                        .toSet()
                        .sortedBy(Educator::lastViewTime)

                // TODO may be update cached educators

                localList + remoteList.toMutableList().also { it.removeAll(localList) }
            }
    )

    override fun isFavorite(educator: Educator): Single<Boolean> = localDataSource.isFavorite(educator)

    override fun getFavorites(): Maybe<List<Educator>> = localDataSource.getFavorites()

    override fun setFavorite(educator: Educator, isFavorite: Boolean): Single<Educator> = localDataSource
            .update(educator.also { it.isFavorite = isFavorite })
            .andThen(Single.just(educator))

    override fun getViewed(): Maybe<List<Educator>> = localDataSource.getViewed()

    override fun setViewed(educator: Educator): Single<Educator> = localDataSource
            .update(educator.also { it.view(timeUtils.getCurrentTime()) })
            .andThen(Single.just(educator))

}
