package ru.blizzed.timetablespbu.data.datasources

import io.reactivex.*
import ru.blizzed.timetablespbu.data.DBScheduler
import ru.blizzed.timetablespbu.data.persistance.dao.EducatorsDao
import ru.blizzed.timetablespbu.data.persistance.mappers.EducatorDataMapper.mapToData
import ru.blizzed.timetablespbu.data.persistance.mappers.EducatorDataMapper.mapToEntity
import ru.blizzed.timetablespbu.domain.entities.Educator
import javax.inject.Inject

class EducatorsLocalDataSource @Inject constructor(
        private val educatorsDao: EducatorsDao,
        @DBScheduler dbScheduler: Scheduler
) : DatabaseDataSource(dbScheduler) {

    fun getAll(query: String): Single<List<Educator>> = educatorsDao
            .getAll(query)
            .mapToEntity()
            .onDbScheduler()

    fun observeAll(): Flowable<List<Educator>> = educatorsDao
            .observeAll()
            .mapToEntity()
            .onDbScheduler()

    fun observeFavorites(): Flowable<List<Educator>> = educatorsDao
            .observeFavorites()
            .mapToEntity()
            .onDbScheduler()

    fun observeViewed(): Flowable<List<Educator>> = educatorsDao
            .observeViewed()
            .mapToEntity()
            .onDbScheduler()

    fun observeNonFavoriteViewed(): Flowable<List<Educator>> = educatorsDao
            .observeNonFavoriteViewed()
            .mapToEntity()
            .onDbScheduler()

    fun updateOrAdd(educator: Educator): Completable = Completable
            .fromAction { educatorsDao.updateOrAdd(educator.mapToData()) }
            .onDbScheduler()

    fun delete(educator: Educator): Completable = Completable
            .fromAction { educatorsDao.delete(educator.mapToData()) }
            .onDbScheduler()

    fun isFavorite(educator: Educator): Single<Boolean> = educatorsDao
            .isFavorite(educator.id)
            .onDbScheduler()

    fun getFavorites(): Maybe<List<Educator>> = educatorsDao
            .getFavorites()
            .mapToEntity()
            .onDbScheduler()

    fun getViewed(): Maybe<List<Educator>> = educatorsDao
            .getViewed()
            .mapToEntity()
            .onDbScheduler()

}
