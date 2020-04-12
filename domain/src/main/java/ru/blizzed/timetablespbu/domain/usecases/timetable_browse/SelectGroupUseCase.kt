package ru.blizzed.timetablespbu.domain.usecases.timetable_browse

import ru.blizzed.timetablespbu.domain.entities.Group
import ru.blizzed.timetablespbu.domain.repositories.GroupSelectionRepository

class SelectGroupUseCase(private val groupSelectionRepository: GroupSelectionRepository) {

  operator fun invoke(group: Group) {
    with(groupSelectionRepository) {
      if (group in getAllGroups()) {
        removeGroup(group)
      } else {
        addGroup(group)
      }
    }
  }

}
