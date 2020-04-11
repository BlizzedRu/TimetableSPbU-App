package ru.blizzed.timetablespbu.ui.screens.common.group_search

import io.reactivex.Single
import ru.blizzed.timetablespbu.domain.entities.AdmissionYear
import ru.blizzed.timetablespbu.domain.entities.StudyProgramCombination
import ru.blizzed.timetablespbu.domain.usecases.GroupSearchUseCase
import ru.blizzed.timetablespbu.ui.screens.common.group_search.base.BaseSelectionStepViewModel

class AdmissionYearStepViewModel(
  private val groupSearchUseCase: GroupSearchUseCase
) : BaseSelectionStepViewModel<StudyProgramCombination, AdmissionYear>() {



  override fun loadItems(): Single<List<StudyProgramCombination>> {
//    groupSearchUseCase.getStudyProgramCombinationsByLevel()
    TODO()
  }

  override fun onItemSelected(item: AdmissionYear) {
    TODO("Not yet implemented")
  }
}
