package ru.blizzed.timetablespbu.ui.screens.common.group_search

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_base_group_selection_step.recycler
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.domain.entities.Faculty
import ru.blizzed.timetablespbu.ui.screens.common.faculty_search.FacultiesAdapter
import ru.blizzed.timetablespbu.ui.screens.common.group_search.base.BaseSelectionStepFragment

class FacultySelectionStepFragment :
  BaseSelectionStepFragment<Faculty, Faculty, FacultySelectionStepViewModel>() {

  override val viewModel: FacultySelectionStepViewModel by viewModel()

  override val titleRes: Int = R.string.welcome_student_group_search_step_faculty_title

  private val adapter by lazy {
    FacultiesAdapter().also {
      it.onItemClickListener = { faculty, _ -> onItemSelected(faculty) }
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    recycler.adapter = adapter
  }

  override fun renderLoaded(items: List<Faculty>) {
    adapter.submitItems(items)
  }

}
