package ru.blizzed.timetablespbu.data.persistance.mappers

import ru.blizzed.timetablespbu.data.persistance.entities.EducatorEmploymentData
import ru.blizzed.timetablespbu.domain.entities.EducatorEmployment

object EducatorEmploymentDataMapper : DataMapper<EducatorEmploymentData, EducatorEmployment> {

    override fun EducatorEmploymentData.mapToEntity() = EducatorEmployment(post, department)

    override fun EducatorEmployment.mapToData() = EducatorEmploymentData(post, department)

}
