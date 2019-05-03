package ru.blizzed.timetablespbu.domain.mappers

import ru.blizzed.timetablespbu.domain.entities.Classroom
import javax.inject.Inject

class ClassroomMapper @Inject constructor() : Mapper<ru.blizzed.timetablespbulib.model.addresses.Classroom, Classroom> {

    override fun apply(input: ru.blizzed.timetablespbulib.model.addresses.Classroom): Classroom = Classroom(
            input.oid,
            input.displayName,
            input.capacity,
            input.additionalInfo
    )

}
