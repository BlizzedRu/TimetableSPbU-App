package ru.blizzed.timetablespbu.domain.entities

data class Faculty(
  val name: String,
  val alias: FacultyAlias,
  var logo: String,
  val addresses: List<Address>?
)

typealias FacultyAlias = String
