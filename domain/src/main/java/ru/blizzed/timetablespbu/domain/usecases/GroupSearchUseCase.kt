package ru.blizzed.timetablespbu.domain.usecases

import ru.blizzed.timetablespbu.domain.entities.FacultyAlias
import ru.blizzed.timetablespbu.domain.entities.Group
import ru.blizzed.timetablespbu.domain.repositories.FacultiesRepository
import ru.blizzed.timetablespbu.domain.repositories.GroupSearchRepository
import ru.blizzed.timetablespbu.domain.repositories.GroupSelectionRepository

class GroupSearchUseCase(
  private val groupSearchRepository: GroupSearchRepository,
  private val facultiesRepository: FacultiesRepository,
  private val groupSelectionRepository: GroupSelectionRepository
) {

  fun getAllFaculties() = facultiesRepository.getAll()

  fun getStudyLevelsByDivisionAlias(facultyAlias: FacultyAlias) =
    groupSearchRepository.getStudyLevelsByDivisionAlias(facultyAlias)

  fun getProgramCombinations(facultyAlias: FacultyAlias, studyLevelId: Int) =
    groupSearchRepository.getProgramCombinations(facultyAlias, studyLevelId)

  fun getGroupsByProgramId(programId: Int) = groupSearchRepository.getGroupsByProgramId(programId)

  fun selectGroup(group: Group) {
    with(groupSelectionRepository) {
      if (group in getAllGroups()) {
        removeGroup(group)
      } else {
        addGroup(group)
      }
    }
  }

  fun observeGroups() = groupSelectionRepository.observeGroups()

}
