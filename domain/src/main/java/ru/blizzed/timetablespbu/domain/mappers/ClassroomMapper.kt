package ru.blizzed.timetablespbu.domain.mappers

import ru.blizzed.timetablespbu.domain.entities.Classroom

object ClassroomMapper : Mapper<ru.blizzed.timetablespbulib.model.addresses.Classroom, Classroom> {

    override fun ru.blizzed.timetablespbulib.model.addresses.Classroom.mapToEntity(): Classroom = Classroom(oid, displayName, capacity, additionalInfo)

}
