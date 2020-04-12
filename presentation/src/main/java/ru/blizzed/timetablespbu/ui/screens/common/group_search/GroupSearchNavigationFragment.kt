package ru.blizzed.timetablespbu.ui.screens.common.group_search

import android.os.Bundle
import android.view.View
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import ru.blizzed.timetablespbu.core.BaseNavigationFragment
import ru.blizzed.timetablespbu.core.NavigationActivity
import ru.blizzed.timetablespbu.ui.screens.common.group_search.base.SelectionStepsSharedViewModel

class GroupSearchNavigationFragment: BaseNavigationFragment<NavigationActivity>() {

  private val sharedViewModel: SelectionStepsSharedViewModel by sharedViewModel()

  override val layoutRes: Int = ru.blizzed.timetablespbu.R.layout.fragment_group_selection

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    sharedViewModel
  }

}
