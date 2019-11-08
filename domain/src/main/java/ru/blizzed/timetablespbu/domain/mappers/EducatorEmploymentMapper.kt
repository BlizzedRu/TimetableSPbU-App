package ru.blizzed.timetablespbu.domain.mappers

import ru.blizzed.timetablespbu.domain.entities.EducatorEmployment
import ru.blizzed.timetablespbulib.model.educators.Educator

object EducatorEmploymentMapper : Mapper<Educator.Employment, EducatorEmployment> {

    override fun Educator.Employment.mapToEntity() = EducatorEmployment(position, department)

}
