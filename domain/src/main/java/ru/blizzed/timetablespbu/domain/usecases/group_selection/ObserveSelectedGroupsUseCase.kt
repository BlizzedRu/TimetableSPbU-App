package ru.blizzed.timetablespbu.domain.usecases.group_selection

import ru.blizzed.timetablespbu.domain.repositories.GroupSelectionRepository

class ObserveSelectedGroupsUseCase(private val groupSelectionRepository: GroupSelectionRepository) {

  operator fun invoke() = groupSelectionRepository.observeGroups()

}
