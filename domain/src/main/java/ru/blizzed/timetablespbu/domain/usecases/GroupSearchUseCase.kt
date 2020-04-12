package ru.blizzed.timetablespbu.domain.usecases

import ru.blizzed.timetablespbu.domain.entities.FacultyAlias
import ru.blizzed.timetablespbu.domain.repositories.FacultiesRepository
import ru.blizzed.timetablespbu.domain.repositories.GroupSearchRepository

class GroupSearchUseCase(
  private val groupSearchRepository: GroupSearchRepository,
  private val facultiesRepository: FacultiesRepository
) {

  fun getAllFaculties() = facultiesRepository.getAll()

  fun getStudyLevelsByDivisionAlias(facultyAlias: FacultyAlias) =
    groupSearchRepository.getStudyLevelsByDivisionAlias(facultyAlias)

  fun getProgramCombinations(facultyAlias: FacultyAlias, studyLevelId: Int) =
    groupSearchRepository.getProgramCombinations(facultyAlias, studyLevelId)

  fun getGroupsByProgramId(programId: Int) = groupSearchRepository.getGroupsByProgramId(programId)

}
