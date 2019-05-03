package ru.blizzed.timetablespbu.data.persistance.mappers

import ru.blizzed.timetablespbu.data.persistance.entities.ClassroomData
import ru.blizzed.timetablespbu.domain.entities.Classroom

class ClassroomDataMapper : DataMapper<Classroom, ClassroomData> {

    override fun mapToEntity(input: Classroom): ClassroomData = ClassroomData(
            input.oid,
            input.name,
            input.capacity,
            input.additionalInfo
    )

    override fun mapToData(input: ClassroomData): Classroom = Classroom(
            input.oid,
            input.name,
            input.capacity,
            input.additionalInfo
    )

}
