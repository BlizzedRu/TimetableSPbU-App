package ru.blizzed.timetablespbu.domain.usecases

import io.reactivex.Single
import ru.blizzed.timetablespbu.domain.repositories.UserInfoRepository

class UserInfoUseCase(private val userInfoRepository: UserInfoRepository) {

    fun isLoggedIn(): Single<Boolean> = userInfoRepository.hasInfo()

}
