package ru.blizzed.timetablespbu.domain.repositories

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import ru.blizzed.timetablespbu.domain.entities.UserInfo

interface UserInfoRepository {

    /**
     * Tells if somebody ever logged the app in
     */
    fun isLoggedIn(): Single<Boolean>

    fun getInfo(): Maybe<UserInfo>

    fun setInfo(userInfo: UserInfo): Completable

}
