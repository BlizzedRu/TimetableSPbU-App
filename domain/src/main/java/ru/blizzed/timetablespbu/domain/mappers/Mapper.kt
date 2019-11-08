package ru.blizzed.timetablespbu.domain.mappers

interface Mapper<I, O> {

    fun I.mapToEntity() : O

    fun List<I>.mapToEntity(): List<O>  = map { it.mapToEntity() }

}
