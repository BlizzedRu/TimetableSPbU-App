package ru.blizzed.timetablespbu.ui.screens.common.group_search.admission_year

import android.os.Bundle
import io.reactivex.Single
import ru.blizzed.timetablespbu.domain.entities.StudyProgramCombination
import ru.blizzed.timetablespbu.domain.entities.TimetableBrowseData
import ru.blizzed.timetablespbu.domain.usecases.timetable_browse.GetProgramCombinationsUseCase
import ru.blizzed.timetablespbu.ui.screens.common.group_search.base.BaseSelectionStepViewModel

class AdmissionYearStepViewModel(
  private val getProgramCombinations: GetProgramCombinationsUseCase
) : BaseSelectionStepViewModel<StudyProgramCombination, StudyProgramCombination>() {

  private lateinit var browseData: TimetableBrowseData

  init {
    onInitialized()
  }

  override fun loadItems(): Single<List<StudyProgramCombination>> =
    getProgramCombinations(browseData.facultyAlias, checkNotNull(browseData.studyLevelId))

  override fun onItemSelected(item: StudyProgramCombination) {
    navigate(
      AdmissionYearSelectionStepFragmentDirections
        .actionAdmissionYearSelectionStepToGroupSelectionStep(browseData.copy(programId = item.id))
    )
  }

  override fun observeArguments(bundle: Bundle) {
    browseData = AdmissionYearSelectionStepFragmentArgs.fromBundle(bundle).browseData
  }
}
