package ru.blizzed.timetablespbu.domain.mappers

import ru.blizzed.timetablespbu.domain.entities.EducatorEmployment
import ru.blizzed.timetablespbulib.model.educators.Educator
import javax.inject.Inject

class EducatorEmploymentMapper @Inject constructor(): Mapper<Educator.Employment, EducatorEmployment> {

    override fun apply(input: Educator.Employment) = EducatorEmployment(
        input.position,
        input.department
    )

}