package ru.blizzed.timetablespbu.data.persistance.mappers

interface DataMapper<DataType, EntityType> {

    fun mapToEntity(input: DataType): EntityType

    fun mapToData(input: EntityType): DataType

    fun mapToEntity(inputList: List<DataType>): List<EntityType> = inputList.map(::mapToEntity)

    fun mapToData(inputList: List<EntityType>): List<DataType> = inputList.map(::mapToData)

}
