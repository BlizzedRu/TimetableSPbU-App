package ru.blizzed.timetablespbu.domain.usecases.timetable_browse

import ru.blizzed.timetablespbu.domain.entities.Group
import ru.blizzed.timetablespbu.domain.repositories.GroupSelectionRepository

class RemoveGroupUseCase(private val groupSelectionRepository: GroupSelectionRepository) {

  operator fun invoke(group: Group) = groupSelectionRepository.removeGroup(group)
  
}
