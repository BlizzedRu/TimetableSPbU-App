package ru.blizzed.timetablespbu.domain.usecases.group_selection

import ru.blizzed.timetablespbu.domain.entities.FacultyAlias
import ru.blizzed.timetablespbu.domain.repositories.GroupSearchRepository

class GetProgramCombinationsUseCase(private val groupSearchRepository: GroupSearchRepository) {

  operator fun invoke(facultyAlias: FacultyAlias, studyLevelId: Int) =
    groupSearchRepository.getProgramCombinations(facultyAlias, studyLevelId)

}
