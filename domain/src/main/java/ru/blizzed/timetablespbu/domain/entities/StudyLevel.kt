package ru.blizzed.timetablespbu.domain.entities

data class StudyLevel(
  val id: Int,
  val name: String,
  val studyProgramCombinations: List<StudyProgramCombination>
)

data class StudyProgramCombination(
  val name: String,
  val admissionYears: List<AdmissionYear>
)

data class AdmissionYear(
  val studyProgramId: Int,
  val year: Int
)
