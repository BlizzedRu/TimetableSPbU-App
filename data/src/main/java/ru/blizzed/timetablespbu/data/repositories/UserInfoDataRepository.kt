package ru.blizzed.timetablespbu.data.repositories

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import ru.blizzed.timetablespbu.data.datasources.UserInfoDataSource
import ru.blizzed.timetablespbu.domain.entities.UserInfo
import ru.blizzed.timetablespbu.domain.repositories.UserInfoRepository

class UserInfoDataRepository(private val userInfoDataSource: UserInfoDataSource) : UserInfoRepository {

    override fun hasInfo(): Single<Boolean> = userInfoDataSource.isEmpty().map { !it }

    override fun getInfo(): Maybe<UserInfo> = userInfoDataSource.get()

    override fun setInfo(userInfo: UserInfo): Completable = userInfoDataSource.set(userInfo)
}
