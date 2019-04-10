package ru.blizzed.timetablespbu.data.persistance.mappers.data

import ru.blizzed.timetablespbu.data.persistance.entities.EducatorData
import ru.blizzed.timetablespbu.domain.entities.Educator
import ru.blizzed.timetablespbu.domain.mappers.Mapper
import javax.inject.Inject

class EducatorDataMapper @Inject constructor(
        private val employmentMapper: EducatorEmploymentMapper
) : Mapper<Educator, EducatorData> {

    override fun apply(input: Educator) = EducatorData(
            input.id,
            input.fullName,
            employmentMapper.apply(input.employments)
    )

}