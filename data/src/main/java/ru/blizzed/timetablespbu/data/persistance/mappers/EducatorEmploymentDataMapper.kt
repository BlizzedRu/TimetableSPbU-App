package ru.blizzed.timetablespbu.data.persistance.mappers

import ru.blizzed.timetablespbu.data.persistance.entities.EducatorEmploymentData
import ru.blizzed.timetablespbu.domain.entities.EducatorEmployment
import javax.inject.Inject

class EducatorEmploymentDataMapper @Inject constructor() : DataMapper<EducatorEmploymentData, EducatorEmployment> {

    override fun mapToEntity(input: EducatorEmploymentData) = EducatorEmployment(
            input.post,
            input.department
    )

    override fun mapToData(input: EducatorEmployment) = EducatorEmploymentData(
            input.post,
            input.department
    )

}
