package ru.blizzed.timetablespbu.data.persistance.mappers

interface DataMapper<DataType, EntityType> {

    fun DataType.mapToEntity(): EntityType

    fun EntityType.mapToData(): DataType

    fun List<DataType>.mapToEntity(): List<EntityType> = map { it.mapToEntity() }

    fun List<EntityType>.mapToData(): List<DataType> = map { it.mapToData() }

}
