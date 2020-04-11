package ru.blizzed.timetablespbu.ui.screens.common.group_search

import ru.blizzed.timetablespbu.domain.entities.AdmissionYear
import ru.blizzed.timetablespbu.domain.entities.StudyLevel
import ru.blizzed.timetablespbu.domain.entities.StudyProgramCombination
import ru.blizzed.timetablespbu.domain.usecases.GroupSearchUseCase
import ru.blizzed.timetablespbu.ui.screens.common.group_search.base.BaseSelectionStepViewModel

class AdmissionYearStepViewModel(
  private val groupSearchUseCase: GroupSearchUseCase
) : BaseSelectionStepViewModel<StudyProgramCombination, AdmissionYear, StudyLevel>() {

  override fun loadItems(param: StudyLevel) = groupSearchUseCase.getStudyProgramCombinationsByLevel(param)

  override fun onItemSelected(item: AdmissionYear) {
    TODO("Not yet implemented")
  }
}
