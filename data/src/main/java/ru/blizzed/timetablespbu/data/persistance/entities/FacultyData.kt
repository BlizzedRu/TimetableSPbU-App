package ru.blizzed.timetablespbu.data.persistance.entities

data class FacultyData(
        val name: String,
        val alias: String,
        var logo: String,
        val addresses: List<AddressData>?
)
