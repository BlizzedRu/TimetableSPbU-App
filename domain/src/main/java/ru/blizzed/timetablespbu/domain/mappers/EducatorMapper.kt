package ru.blizzed.timetablespbu.domain.mappers

import ru.blizzed.timetablespbu.domain.entities.Educator
import javax.inject.Inject

class EducatorMapper @Inject constructor(
        private val employmentMapper: EducatorEmploymentMapper
) : Mapper<ru.blizzed.timetablespbulib.model.educators.Educator, Educator> {

    override fun apply(input: ru.blizzed.timetablespbulib.model.educators.Educator) = Educator(
            input.id,
            input.fullName,
            employmentMapper.apply(input.employments)
    )

}
