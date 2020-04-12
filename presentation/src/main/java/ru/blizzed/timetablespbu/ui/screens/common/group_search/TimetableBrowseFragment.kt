package ru.blizzed.timetablespbu.ui.screens.common.group_search

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.fragment_timetable_browse.selectedGroups
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.core.navigation.NavigationFragment
import ru.blizzed.timetablespbu.ui.screens.common.group_search.base.SelectionStepsSharedViewModel

class TimetableBrowseFragment : NavigationFragment<TimetableBrowseViewModel>(R.layout.fragment_timetable_browse) {

  private val sharedViewModel: SelectionStepsSharedViewModel by sharedViewModel()

  override val viewModel: TimetableBrowseViewModel by viewModel()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    viewModel.observeState(viewLifecycleOwner, ::renderState)
  }

  private fun renderState(state: ViewState) {
    with(selectedGroups) {
      isVisible = state.selectedGroupsVisible
      setGroups(state.selectedGroups)
      onRemoveClickListener = { group ->
        viewModel.dispatchEvent(ViewEvent.OnGroupRemoveClicked(group))
      }
    }
  }

}
