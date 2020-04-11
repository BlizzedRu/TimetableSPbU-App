package ru.blizzed.timetablespbu.ui.screens.common.group_search

import io.reactivex.Single
import ru.blizzed.timetablespbu.domain.entities.Group
import ru.blizzed.timetablespbu.domain.usecases.GroupSearchUseCase
import ru.blizzed.timetablespbu.ui.screens.common.group_search.base.BaseSelectionStepViewModel

class GroupStepViewModel(
  private val groupSearchUseCase: GroupSearchUseCase
) : BaseSelectionStepViewModel<Group, Group>() {

  override fun loadItems(): Single<List<Group>> {
//    groupSearchUseCase.getGroupsByAdmissionYear(param)

    TODO()
  }

  override fun onItemSelected(item: Group) {
    TODO("Not yet implemented")
  }
}
