package ru.blizzed.timetablespbu.ui.screens.common.group_search.group

import android.os.Bundle
import io.reactivex.Single
import ru.blizzed.timetablespbu.domain.entities.Group
import ru.blizzed.timetablespbu.domain.usecases.GroupSearchUseCase
import ru.blizzed.timetablespbu.ui.screens.common.group_search.base.BaseSelectionStepViewModel

class GroupStepViewModel(
  private val groupSearchUseCase: GroupSearchUseCase
) : BaseSelectionStepViewModel<Group, Group>() {

  private var studyProgramId: Int = -1

  init {
    onInitialized()
  }

  override fun loadItems(): Single<List<Group>> = groupSearchUseCase.getGroupsByProgramId(studyProgramId)

  override fun onItemSelected(item: Group) {
    groupSearchUseCase.selectGroup(item)
  }

  override fun observeArguments(bundle: Bundle) {
    studyProgramId = GroupSelectionStepFragmentArgs.fromBundle(bundle).programId
  }
}
