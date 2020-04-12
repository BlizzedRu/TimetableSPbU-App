package ru.blizzed.timetablespbu.ui.screens.common.group_search.group

import android.os.Bundle
import io.reactivex.Single
import ru.blizzed.timetablespbu.domain.entities.AdmissionYearGroups
import ru.blizzed.timetablespbu.domain.entities.Group
import ru.blizzed.timetablespbu.domain.entities.TimetableBrowseData
import ru.blizzed.timetablespbu.domain.usecases.timetable_browse.GetAdmissionYearGroupsUseCase
import ru.blizzed.timetablespbu.domain.usecases.timetable_browse.SelectGroupUseCase
import ru.blizzed.timetablespbu.ui.screens.common.group_search.base.BaseSelectionStepViewModel

class GroupStepViewModel(
  private val getAdmissionYearGroups: GetAdmissionYearGroupsUseCase,
  private val selectGroup: SelectGroupUseCase
) : BaseSelectionStepViewModel<AdmissionYearGroups, Group>() {

  private lateinit var browseData: TimetableBrowseData

  init {
    onInitialized()
  }

  override fun loadItems(): Single<List<AdmissionYearGroups>> = getAdmissionYearGroups(
    browseData.facultyAlias,
    checkNotNull(browseData.studyLevelId),
    checkNotNull(browseData.programId)
  )

  override fun onItemSelected(item: Group) = selectGroup(item)

  override fun observeArguments(bundle: Bundle) {
    browseData = GroupSelectionStepFragmentArgs.fromBundle(bundle).browseData
  }
}
