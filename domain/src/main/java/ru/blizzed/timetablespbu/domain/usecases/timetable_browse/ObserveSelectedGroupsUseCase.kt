package ru.blizzed.timetablespbu.domain.usecases.timetable_browse

import ru.blizzed.timetablespbu.domain.repositories.GroupSelectionRepository

class ObserveSelectedGroupsUseCase(private val groupSelectionRepository: GroupSelectionRepository) {

  operator fun invoke() = groupSelectionRepository.observeGroups()

}
