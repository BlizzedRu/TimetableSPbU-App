package ru.blizzed.timetablespbu.domain.entities

data class StudyLevel(
  val id: Int,
  val name: String,
  val studyProgramCombinations: List<StudyProgramCombination>
)

data class StudyProgramCombination(
  val name: String,
  val admissionYears: List<AdmissionYear>
) {
  val id: Int = name.hashCode()
}

data class AdmissionYear(
  val studyProgramId: StudyProgramId,
  val year: Int
) {
  val id: Int = studyProgramId
}

data class AdmissionYearGroups(
  val admissionYear: AdmissionYear,
  val groups: List<Group>
) {
  val id: Int = admissionYear.id
}

typealias StudyProgramId = Int
