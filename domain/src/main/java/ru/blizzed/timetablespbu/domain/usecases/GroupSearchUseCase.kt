package ru.blizzed.timetablespbu.domain.usecases

import io.reactivex.Single
import ru.blizzed.timetablespbu.domain.entities.AdmissionYear
import ru.blizzed.timetablespbu.domain.entities.FacultyAlias
import ru.blizzed.timetablespbu.domain.entities.StudyLevel
import ru.blizzed.timetablespbu.domain.repositories.FacultiesRepository
import ru.blizzed.timetablespbu.domain.repositories.GroupSearchRepository

class GroupSearchUseCase(
  private val groupSearchRepository: GroupSearchRepository,
  private val facultiesRepository: FacultiesRepository
) {

  fun getAllFaculties() = facultiesRepository.getAll()

  fun getStudyLevelsByDivisionAlias(facultyAlias: FacultyAlias) =
    groupSearchRepository.getStudyLevelsByDivisionAlias(facultyAlias)

  fun getStudyProgramCombinationsByLevel(studyLevel: StudyLevel) = Single.just(studyLevel.studyProgramCombinations)

  fun getProgramCombinations(facultyAlias: FacultyAlias, studyLevelId: Int) =
    groupSearchRepository.getProgramCombinations(facultyAlias, studyLevelId)

  fun getGroupsByAdmissionYear(admissionYear: AdmissionYear) =
    groupSearchRepository.getGroupsByProgramId(admissionYear.studyProgramId)

}
