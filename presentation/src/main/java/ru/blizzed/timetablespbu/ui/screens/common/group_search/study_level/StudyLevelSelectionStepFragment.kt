package ru.blizzed.timetablespbu.ui.screens.common.group_search.study_level

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_base_group_selection_step.recycler
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.domain.entities.StudyLevel
import ru.blizzed.timetablespbu.ui.screens.common.group_search.base.BaseSelectionStepAdapter
import ru.blizzed.timetablespbu.ui.screens.common.group_search.base.BaseSelectionStepFragment

class StudyLevelSelectionStepFragment : BaseSelectionStepFragment<StudyLevel, StudyLevel, StudyLevelStepViewModel>() {

  override val viewModel: StudyLevelStepViewModel by viewModel()

  override val titleRes: Int = R.string.welcome_student_group_search_step_level_title

  private val adapter by lazy {
    BaseSelectionStepAdapter(StudyLevel::name).also {
      it.onItemClickListener = { item, _ ->
        onItemSelected(item)
      }
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    recycler.adapter = adapter
  }

  override fun renderLoaded(items: List<StudyLevel>) {
    adapter.submitItems(items)
  }
}

