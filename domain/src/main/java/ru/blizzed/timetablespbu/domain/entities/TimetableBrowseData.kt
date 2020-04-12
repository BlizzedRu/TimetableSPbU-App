package ru.blizzed.timetablespbu.domain.entities

import java.io.Serializable

data class TimetableBrowseData(
  val facultyAlias: FacultyAlias,
  val studyLevelId: Int? = null,
  val programId: Int? = null,
  val groupId: Int? = null
) : Serializable
