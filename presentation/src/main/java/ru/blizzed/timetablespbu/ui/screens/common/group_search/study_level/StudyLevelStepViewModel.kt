package ru.blizzed.timetablespbu.ui.screens.common.group_search.study_level

import io.reactivex.Single
import ru.blizzed.timetablespbu.domain.entities.StudyLevel
import ru.blizzed.timetablespbu.domain.usecases.GroupSearchUseCase
import ru.blizzed.timetablespbu.ui.screens.common.group_search.base.BaseSelectionStepViewModel

class StudyLevelStepViewModel(
  private val groupSearchUseCase: GroupSearchUseCase
) : BaseSelectionStepViewModel<StudyLevel, StudyLevel>() {

  override fun loadItems(): Single<List<StudyLevel>> =
    groupSearchUseCase.getStudyLevelsByDivisionAlias("param")

  override fun onItemSelected(item: StudyLevel) {
    TODO("Not yet implemented")
  }
}
