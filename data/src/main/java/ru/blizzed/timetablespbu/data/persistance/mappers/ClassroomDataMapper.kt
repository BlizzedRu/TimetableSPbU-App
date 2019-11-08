package ru.blizzed.timetablespbu.data.persistance.mappers

import ru.blizzed.timetablespbu.data.persistance.entities.ClassroomData
import ru.blizzed.timetablespbu.domain.entities.Classroom

object ClassroomDataMapper : DataMapper<Classroom, ClassroomData> {

    override fun Classroom.mapToEntity(): ClassroomData = ClassroomData(oid, name, capacity, additionalInfo)

    override fun ClassroomData.mapToData(): Classroom = Classroom(oid, name, capacity, additionalInfo)

}
