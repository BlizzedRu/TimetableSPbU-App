package ru.blizzed.timetablespbu.ui.screens.common.group_search.admission_year

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_base_group_selection_step.recycler
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.domain.entities.AdmissionYear
import ru.blizzed.timetablespbu.domain.entities.StudyProgramCombination
import ru.blizzed.timetablespbu.ui.screens.common.group_search.base.BaseSelectionStepFragment

class AdmissionYearSelectionStepFragment :
  BaseSelectionStepFragment<StudyProgramCombination, AdmissionYear, AdmissionYearStepViewModel>() {

  override val viewModel: AdmissionYearStepViewModel by viewModel()

  override val titleRes: Int = R.string.welcome_student_group_search_step_admission_year_title

  private val adapter by lazy { StudyProgramCombinationsAdapter(::onItemSelected) }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    recycler.adapter = adapter
  }

  override fun submitItems(items: List<StudyProgramCombination>) {
    adapter.submitItems(items)
  }

}
