package ru.blizzed.timetablespbu.data.persistance.mappers.data

import ru.blizzed.timetablespbu.data.persistance.entities.EducatorEmploymentData
import ru.blizzed.timetablespbu.domain.entities.EducatorEmployment
import ru.blizzed.timetablespbu.domain.mappers.Mapper
import javax.inject.Inject

class EducatorEmploymentMapper @Inject constructor() : Mapper<EducatorEmployment, EducatorEmploymentData> {

    override fun apply(input: EducatorEmployment) = EducatorEmploymentData(
            input.post,
            input.department
    )

}