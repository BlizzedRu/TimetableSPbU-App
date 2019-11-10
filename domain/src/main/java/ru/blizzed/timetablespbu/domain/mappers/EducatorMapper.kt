package ru.blizzed.timetablespbu.domain.mappers

import ru.blizzed.timetablespbu.domain.entities.Educator
import ru.blizzed.timetablespbu.domain.mappers.EducatorEmploymentMapper.mapToEntity
import ru.blizzed.timetablespbulib.model.educators.Educator as ApiEducator

object EducatorMapper : Mapper<ApiEducator, Educator> {

    override fun ApiEducator.mapToEntity() = Educator(id, fullName, employments.mapToEntity())

}
