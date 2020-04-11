package ru.blizzed.timetablespbu.ui.screens.common.group_search

import io.reactivex.Single
import ru.blizzed.timetablespbu.domain.entities.Faculty
import ru.blizzed.timetablespbu.domain.usecases.GroupSearchUseCase
import ru.blizzed.timetablespbu.ui.screens.common.group_search.base.BaseSelectionStepViewModel

class FacultySelectionStepViewModel(
  private val groupSearchUseCase: GroupSearchUseCase
) : BaseSelectionStepViewModel<Faculty, Faculty>() {

  init {
    onInitialized()
  }

  override fun loadItems(): Single<List<Faculty>> = groupSearchUseCase.getAllFaculties()

  override fun onItemSelected(item: Faculty) {
    navigate(FacultySelectionStepFragmentDirections.actionFacultySelectionStepToStudyLevelSelectionStep(item.alias))
  }
}
