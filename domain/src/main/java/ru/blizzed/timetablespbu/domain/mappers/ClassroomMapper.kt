package ru.blizzed.timetablespbu.domain.mappers

import ru.blizzed.timetablespbu.domain.entities.Classroom
import ru.blizzed.timetablespbulib.model.addresses.Classroom as ApiClassroom

object ClassroomMapper : Mapper<ApiClassroom, Classroom> {

    override fun ApiClassroom.mapToEntity(): Classroom = Classroom(oid, displayName, capacity, additionalInfo)

}
