package ru.blizzed.timetablespbu.data.datasources

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Scheduler
import io.reactivex.Single
import ru.blizzed.timetablespbu.data.persistance.dao.UserInfoDao
import ru.blizzed.timetablespbu.data.persistance.mappers.UserInfoDataMapper.mapToData
import ru.blizzed.timetablespbu.data.persistance.mappers.UserInfoDataMapper.mapToEntity
import ru.blizzed.timetablespbu.domain.entities.UserInfo

class UserInfoDataSource(
        private val userInfoDao: UserInfoDao,
        dbScheduler: Scheduler
) : DatabaseDataSource(dbScheduler) {

    fun isEmpty(): Single<Boolean> = Single
            .fromCallable {
                userInfoDao.isEmpty()
            }
            .onDbScheduler()

    fun get(): Maybe<UserInfo> = userInfoDao
            .get()
            .map { it.mapToEntity() }
            .onDbScheduler()

    fun set(userInfo: UserInfo): Completable = Completable
            .fromAction { userInfoDao.insert(userInfo.mapToData()) }
            .onDbScheduler()

}
