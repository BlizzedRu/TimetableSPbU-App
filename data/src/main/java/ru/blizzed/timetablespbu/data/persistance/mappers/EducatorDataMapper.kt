package ru.blizzed.timetablespbu.data.persistance.mappers

import ru.blizzed.timetablespbu.data.persistance.entities.EducatorData
import ru.blizzed.timetablespbu.data.persistance.mappers.EducatorEmploymentDataMapper.mapToData
import ru.blizzed.timetablespbu.data.persistance.mappers.EducatorEmploymentDataMapper.mapToEntity
import ru.blizzed.timetablespbu.domain.entities.Educator

object EducatorDataMapper : RxDataMapper<EducatorData, Educator> {

    override fun EducatorData.mapToEntity() = Educator(
            id,
            fullName,
            employments.mapToEntity(),
            circleColor,
            isFavorite,
            isViewed,
            lastInteractionTime
    )

    override fun Educator.mapToData() = EducatorData(
            id,
            fullName,
            employments.mapToData(),
            circleColor,
            isFavorite,
            isViewed,
            lastInteractionTime
    )

}
