package ru.blizzed.timetablespbu.domain.entities

data class Faculty(
        val name: String,
        val alias: String,
        var logo: String,
        val addresses: List<Address>?
)
