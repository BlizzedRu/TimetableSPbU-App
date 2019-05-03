package ru.blizzed.timetablespbu.data.persistance.mappers

import ru.blizzed.timetablespbu.data.persistance.entities.EducatorData
import ru.blizzed.timetablespbu.data.utils.ColorGenerator
import ru.blizzed.timetablespbu.domain.entities.Educator
import ru.blizzed.timetablespbu.domain.entities.Educator.Companion.NO_COLOR
import javax.inject.Inject

class EducatorDataMapper @Inject constructor(
    private val employmentMapper: EducatorEmploymentDataMapper,
    private val colorGenerator: ColorGenerator
) : RxDataMapper<EducatorData, Educator> {

    override fun mapToEntity(input: EducatorData) = Educator(
        input.id,
        input.fullName,
        employmentMapper.mapToEntity(input.employments),
        input.circleColor,
        input.isFavorite,
        input.isViewed,
        input.lastInteractionTime
    )

    override fun mapToData(input: Educator) = EducatorData(
        input.id,
        input.fullName,
        employmentMapper.mapToData(input.employments),
        input.circleColor.takeUnless(NO_COLOR::equals) ?: colorGenerator.randomPastel(),
        input.isFavorite,
        input.isViewed,
        input.lastInteractionTime
    )

}
