package ru.blizzed.timetablespbu.domain.mappers

interface Mapper<I, O> {

    fun apply(input: I) : O

    fun apply(inputList: List<I>): List<O>  = inputList.map(::apply)

}