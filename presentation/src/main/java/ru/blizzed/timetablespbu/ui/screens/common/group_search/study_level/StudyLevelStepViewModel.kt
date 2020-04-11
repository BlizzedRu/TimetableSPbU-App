package ru.blizzed.timetablespbu.ui.screens.common.group_search.study_level

import android.os.Bundle
import io.reactivex.Single
import ru.blizzed.timetablespbu.domain.entities.FacultyAlias
import ru.blizzed.timetablespbu.domain.entities.StudyLevel
import ru.blizzed.timetablespbu.domain.usecases.GroupSearchUseCase
import ru.blizzed.timetablespbu.ui.screens.common.group_search.base.BaseSelectionStepViewModel

class StudyLevelStepViewModel(
  private val groupSearchUseCase: GroupSearchUseCase
) : BaseSelectionStepViewModel<StudyLevel, StudyLevel>() {

  private lateinit var facultyAlias: FacultyAlias

  init {
    onInitialized()
  }

  override fun loadItems(): Single<List<StudyLevel>> = groupSearchUseCase.getStudyLevelsByDivisionAlias(facultyAlias)

  override fun onItemSelected(item: StudyLevel) {
    item.studyProgramCombinations
  }

  override fun observeArguments(bundle: Bundle) {
    facultyAlias = StudyLevelSelectionStepFragmentArgs.fromBundle(bundle).facultyAlias
  }
}
