package ru.blizzed.timetablespbu.domain.mappers

import ru.blizzed.timetablespbu.domain.entities.EducatorEntity
import ru.blizzed.timetablespbulib.model.educators.Educator
import javax.inject.Inject

class EducatorMapper @Inject constructor(
    private val employmentMapper: EducatorEmploymentMapper
) : Mapper<Educator, EducatorEntity> {

    override fun apply(input: Educator) = EducatorEntity(
        input.id,
        input.fullName,
        employmentMapper.apply(input.employments)
    )

}