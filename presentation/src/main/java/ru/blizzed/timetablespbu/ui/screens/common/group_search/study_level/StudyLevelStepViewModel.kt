package ru.blizzed.timetablespbu.ui.screens.common.group_search.study_level

import android.os.Bundle
import io.reactivex.Single
import ru.blizzed.timetablespbu.domain.entities.FacultyAlias
import ru.blizzed.timetablespbu.domain.entities.StudyLevel
import ru.blizzed.timetablespbu.domain.usecases.group_selection.GetStudyLevelsUseCase
import ru.blizzed.timetablespbu.ui.screens.common.group_search.base.BaseSelectionStepViewModel

class StudyLevelStepViewModel(
  private val getStudyLevels: GetStudyLevelsUseCase
) : BaseSelectionStepViewModel<StudyLevel, StudyLevel>() {

  private lateinit var facultyAlias: FacultyAlias

  init {
    onInitialized()
  }

  override fun loadItems(): Single<List<StudyLevel>> = getStudyLevels(facultyAlias)

  override fun onItemSelected(item: StudyLevel) {
    navigate(StudyLevelSelectionStepFragmentDirections.actionStudyLevelSelectionStepToGroupSelectionStep(item.id, facultyAlias))
  }

  override fun observeArguments(bundle: Bundle) {
    facultyAlias = StudyLevelSelectionStepFragmentArgs.fromBundle(bundle).facultyAlias
  }
}
