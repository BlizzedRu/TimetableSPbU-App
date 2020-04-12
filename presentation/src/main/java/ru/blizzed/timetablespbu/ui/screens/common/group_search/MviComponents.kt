package ru.blizzed.timetablespbu.ui.screens.common.group_search

import ru.blizzed.timetablespbu.domain.entities.Group

sealed class StateChange {
  data class UpdateGroups(val groups: List<Group>): StateChange()
}

sealed class ViewEvent {
  data class OnGroupRemoveClicked(val group: Group): ViewEvent()
}

data class ViewState(
  val selectedGroupsVisible: Boolean,
  val selectedGroups: List<Group>
)
