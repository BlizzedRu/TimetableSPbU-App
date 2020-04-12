package ru.blizzed.timetablespbu.ui.screens.common.group_search.faculty

import io.reactivex.Single
import ru.blizzed.timetablespbu.domain.entities.Faculty
import ru.blizzed.timetablespbu.domain.entities.TimetableBrowseData
import ru.blizzed.timetablespbu.domain.usecases.GetFacultiesUseCase
import ru.blizzed.timetablespbu.ui.screens.common.group_search.base.BaseSelectionStepViewModel

class FacultySelectionStepViewModel(
  private val getFaculties: GetFacultiesUseCase
) : BaseSelectionStepViewModel<Faculty, Faculty>() {

  init {
    onInitialized()
  }

  override fun loadItems(): Single<List<Faculty>> = getFaculties()

  override fun onItemSelected(item: Faculty) {
    navigate(
      FacultySelectionStepFragmentDirections
        .actionFacultySelectionStepToStudyLevelSelectionStep(TimetableBrowseData(facultyAlias = item.alias))
    )
  }
}
