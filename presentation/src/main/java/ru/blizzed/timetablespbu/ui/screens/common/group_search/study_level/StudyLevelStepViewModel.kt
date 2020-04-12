package ru.blizzed.timetablespbu.ui.screens.common.group_search.study_level

import android.os.Bundle
import io.reactivex.Single
import ru.blizzed.timetablespbu.domain.entities.StudyLevel
import ru.blizzed.timetablespbu.domain.entities.TimetableBrowseData
import ru.blizzed.timetablespbu.domain.usecases.timetable_browse.GetStudyLevelsUseCase
import ru.blizzed.timetablespbu.ui.screens.common.group_search.base.BaseSelectionStepViewModel

class StudyLevelStepViewModel(
  private val getStudyLevels: GetStudyLevelsUseCase
) : BaseSelectionStepViewModel<StudyLevel, StudyLevel>() {

  private lateinit var browseData: TimetableBrowseData

  init {
    onInitialized()
  }

  override fun loadItems(): Single<List<StudyLevel>> = getStudyLevels(browseData.facultyAlias)

  override fun onItemSelected(item: StudyLevel) {
    navigate(
      StudyLevelSelectionStepFragmentDirections
        .actionStudyLevelSelectionStepToGroupSelectionStep(browseData.copy(studyLevelId = item.id))
    )
  }

  override fun observeArguments(bundle: Bundle) {
    browseData = StudyLevelSelectionStepFragmentArgs.fromBundle(bundle).browseData
  }
}
