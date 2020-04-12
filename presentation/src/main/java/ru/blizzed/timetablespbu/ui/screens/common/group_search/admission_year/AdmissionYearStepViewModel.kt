package ru.blizzed.timetablespbu.ui.screens.common.group_search.admission_year

import android.os.Bundle
import io.reactivex.Single
import ru.blizzed.timetablespbu.domain.entities.AdmissionYear
import ru.blizzed.timetablespbu.domain.entities.FacultyAlias
import ru.blizzed.timetablespbu.domain.entities.StudyProgramCombination
import ru.blizzed.timetablespbu.domain.usecases.group_selection.GetProgramCombinationsUseCase
import ru.blizzed.timetablespbu.ui.screens.common.group_search.base.BaseSelectionStepViewModel

class AdmissionYearStepViewModel(
  private val getProgramCombinations: GetProgramCombinationsUseCase
) : BaseSelectionStepViewModel<StudyProgramCombination, AdmissionYear>() {

  private lateinit var facultyAlias: FacultyAlias
  private var studyLevelId: Int = -1

  init {
    onInitialized()
  }

  override fun loadItems(): Single<List<StudyProgramCombination>> = getProgramCombinations(facultyAlias, studyLevelId)

  override fun onItemSelected(item: AdmissionYear) {
    navigate(AdmissionYearSelectionStepFragmentDirections.actionAdmissionYearSelectionStepToGroupSelectionStep(item.studyProgramId))
  }

  override fun observeArguments(bundle: Bundle) {
    AdmissionYearSelectionStepFragmentArgs.fromBundle(bundle).also {
      facultyAlias = it.facultyAlias
      studyLevelId = it.studyLevelId
    }
  }
}
