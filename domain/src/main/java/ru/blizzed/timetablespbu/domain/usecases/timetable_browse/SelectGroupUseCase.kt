package ru.blizzed.timetablespbu.domain.usecases.timetable_browse

import ru.blizzed.timetablespbu.domain.entities.Group
import ru.blizzed.timetablespbu.domain.repositories.GroupSelectionRepository

class SelectGroupUseCase(
  private val addGroup: AddGroupUseCase,
  private val removeGroup: RemoveGroupUseCase,
  private val groupSelectionRepository: GroupSelectionRepository
) {

  operator fun invoke(group: Group) {
    if (group in groupSelectionRepository.getAllGroups()) {
      removeGroup(group)
    } else {
      addGroup(group)
    }
  }

}
