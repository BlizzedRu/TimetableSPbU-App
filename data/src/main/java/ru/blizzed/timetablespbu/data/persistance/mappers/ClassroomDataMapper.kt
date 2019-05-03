package ru.blizzed.timetablespbu.data.persistance.mappers

import ru.blizzed.timetablespbu.data.persistance.entities.ClassroomData
import ru.blizzed.timetablespbu.domain.entities.Classroom

class ClassroomDataMapper() : DataMapper<Classroom?, ClassroomData?> {

    override fun mapToEntity(input: Classroom?): ClassroomData? = null

    override fun mapToData(input: ClassroomData?): Classroom? = null

}