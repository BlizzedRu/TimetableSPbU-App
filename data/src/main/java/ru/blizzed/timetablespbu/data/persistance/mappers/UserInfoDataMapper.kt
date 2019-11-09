package ru.blizzed.timetablespbu.data.persistance.mappers

import ru.blizzed.timetablespbu.data.persistance.entities.UserInfoData
import ru.blizzed.timetablespbu.domain.entities.UserInfo

object UserInfoDataMapper : RxDataMapper<UserInfoData, UserInfo> {

    override fun UserInfo.mapToData(): UserInfoData = UserInfoData()

    override fun UserInfoData.mapToEntity(): UserInfo = UserInfo(userType = null)

}
