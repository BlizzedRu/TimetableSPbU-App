package ru.blizzed.timetablespbu.data.datasources

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Scheduler
import io.reactivex.Single
import ru.blizzed.timetablespbu.data.DBScheduler
import ru.blizzed.timetablespbu.data.persistance.dao.EducatorsDao
import ru.blizzed.timetablespbu.data.persistance.entities.EducatorData
import ru.blizzed.timetablespbu.data.persistance.mappers.EducatorDataMapper
import ru.blizzed.timetablespbu.data.persistance.mappers.RxDataMapper
import ru.blizzed.timetablespbu.domain.entities.Educator
import javax.inject.Inject

class EducatorsLocalDataSource @Inject constructor(
        private val educatorsDao: EducatorsDao,
        private val educatorDataMapper: EducatorDataMapper,
        @DBScheduler dbScheduler: Scheduler
) : DatabaseDataSource(dbScheduler), RxDataMapper<EducatorData, Educator> by educatorDataMapper {

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

    fun updateOrAdd(educator: Educator): Completable = Completable
            .fromAction { educatorsDao.updateOrAdd(educatorDataMapper.mapToData(educator)) }
            .onDbScheduler()

    fun delete(educator: Educator): Completable = Completable
            .fromAction { educatorsDao.delete(educatorDataMapper.mapToData(educator)) }
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
