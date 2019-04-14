package ru.blizzed.timetablespbu.data.persistance.mappers

import ru.blizzed.timetablespbu.data.persistance.entities.EducatorData
import ru.blizzed.timetablespbu.domain.entities.Educator
import javax.inject.Inject

class EducatorDataMapper @Inject constructor(
    private val employmentMapper: EducatorEmploymentDataMapper
) : RxDataMapper<EducatorData, Educator> {

    override fun mapToEntity(input: EducatorData) = Educator(
        input.id,
        input.fullName,
        employmentMapper.mapToEntity(input.employments),
        input.isFavorite,
        input.isViewed,
        input.lastInteractionTime
    )

    override fun mapToData(input: Educator) = EducatorData(
        input.id,
        input.fullName,
        employmentMapper.mapToData(input.employments),
        input.isFavorite,
        input.isViewed,
        input.lastInteractionTime
    )

}
