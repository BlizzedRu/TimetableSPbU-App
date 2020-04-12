package ru.blizzed.timetablespbu.ui.screens.common.group_search.group

import android.os.Bundle
import io.reactivex.Single
import ru.blizzed.timetablespbu.domain.entities.Group
import ru.blizzed.timetablespbu.domain.entities.StudyProgramId
import ru.blizzed.timetablespbu.domain.usecases.group_selection.GetGroupsByProgramUseCase
import ru.blizzed.timetablespbu.domain.usecases.group_selection.SelectGroupUseCase
import ru.blizzed.timetablespbu.ui.screens.common.group_search.base.BaseSelectionStepViewModel

class GroupStepViewModel(
  private val getGroupsByProgram: GetGroupsByProgramUseCase,
  private val selectGroup: SelectGroupUseCase
) : BaseSelectionStepViewModel<Group, Group>() {

  private var studyProgramId: StudyProgramId = -1

  init {
    onInitialized()
  }

  override fun loadItems(): Single<List<Group>> = getGroupsByProgram(studyProgramId)

  override fun onItemSelected(item: Group) = selectGroup(item)

  override fun observeArguments(bundle: Bundle) {
    studyProgramId = GroupSelectionStepFragmentArgs.fromBundle(bundle).programId
  }
}
