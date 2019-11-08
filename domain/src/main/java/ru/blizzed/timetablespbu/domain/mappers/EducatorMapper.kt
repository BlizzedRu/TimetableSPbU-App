package ru.blizzed.timetablespbu.domain.mappers

import ru.blizzed.timetablespbu.domain.entities.Educator
import ru.blizzed.timetablespbu.domain.mappers.EducatorEmploymentMapper.mapToEntity

object EducatorMapper : Mapper<ru.blizzed.timetablespbulib.model.educators.Educator, Educator> {

    override fun ru.blizzed.timetablespbulib.model.educators.Educator.mapToEntity() = Educator(id, fullName, employments.mapToEntity())

}
