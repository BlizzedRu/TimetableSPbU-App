package ru.blizzed.timetablespbu.ui.screens.common.group_search.group

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_base_group_selection_step.recycler
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.domain.entities.Group
import ru.blizzed.timetablespbu.ui.screens.common.group_search.base.BaseSelectionStepAdapter
import ru.blizzed.timetablespbu.ui.screens.common.group_search.base.BaseSelectionStepFragment
import ru.blizzed.timetablespbu.ui.screens.common.group_search.base.SelectionStepsSharedViewModel

class GroupSelectionStepFragment : BaseSelectionStepFragment<Group, Group, GroupStepViewModel>() {

  override val viewModel: GroupStepViewModel by viewModel()

  override val titleRes: Int = R.string.welcome_student_group_search_step_group_title

  private val sharedViewModel: SelectionStepsSharedViewModel by sharedViewModel()

  private val adapter by lazy {
    BaseSelectionStepAdapter(Group::alias).also {
      it.onItemClickListener = { item, _ ->
        onItemSelected(item)
        sharedViewModel.onGroupSelected(item)
      }
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    recycler.adapter = adapter
  }

  override fun submitItems(items: List<Group>) {
    adapter.submitItems(items)
  }
}

