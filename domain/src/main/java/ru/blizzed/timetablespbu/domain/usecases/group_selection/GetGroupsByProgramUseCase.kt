package ru.blizzed.timetablespbu.domain.usecases.group_selection

import ru.blizzed.timetablespbu.domain.repositories.GroupSearchRepository

class GetGroupsByProgramUseCase(private val groupSearchRepository: GroupSearchRepository) {

  operator fun invoke(programId: Int) = groupSearchRepository.getGroupsByProgramId(programId)

}
