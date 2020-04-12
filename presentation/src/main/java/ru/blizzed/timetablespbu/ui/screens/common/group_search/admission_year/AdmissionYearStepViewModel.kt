package ru.blizzed.timetablespbu.ui.screens.common.group_search.admission_year

import android.os.Bundle
import io.reactivex.Single
import ru.blizzed.timetablespbu.domain.entities.AdmissionYear
import ru.blizzed.timetablespbu.domain.entities.FacultyAlias
import ru.blizzed.timetablespbu.domain.entities.StudyProgramCombination
import ru.blizzed.timetablespbu.domain.usecases.GroupSearchUseCase
import ru.blizzed.timetablespbu.ui.screens.common.group_search.base.BaseSelectionStepViewModel

class AdmissionYearStepViewModel(
  private val groupSearchUseCase: GroupSearchUseCase
) : BaseSelectionStepViewModel<StudyProgramCombination, AdmissionYear>() {

  private lateinit var facultyAlias: FacultyAlias
  private var studyLevelId: Int = -1

  init {
    onInitialized()
  }

  override fun loadItems(): Single<List<StudyProgramCombination>> =
    groupSearchUseCase.getProgramCombinations(facultyAlias, studyLevelId)

  override fun onItemSelected(item: AdmissionYear) {
  }

  override fun observeArguments(bundle: Bundle) {
    AdmissionYearSelectionStepFragmentArgs.fromBundle(bundle).also {
      facultyAlias = it.facultyAlias
      studyLevelId = it.studyLevelId
    }
  }
}
