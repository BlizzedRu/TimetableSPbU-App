package ru.blizzed.timetablespbu.ui.screens.common.group_search

import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.domain.entities.AdmissionYear
import ru.blizzed.timetablespbu.domain.entities.StudyProgramCombination
import ru.blizzed.timetablespbu.ui.screens.common.group_search.base.BaseSelectionStepAdapter
import ru.blizzed.timetablespbu.ui.screens.common.group_search.base.BaseSelectionStepFragment

class AdmissionYearStepFragment : BaseSelectionStepFragment<StudyProgramCombination, AdmissionYear, AdmissionYearStepViewModel>() {

    override val viewModel: AdmissionYearStepViewModel by viewModel()

    override val titleRes: Int = R.string.welcome_student_group_search_step_program_combination_title

    private val adapter = BaseSelectionStepAdapter(StudyProgramCombination::name).also {
        it.onItemClickListener = { item, _ ->
            onItemSelected(item.admissionYears.first())
        }
    }

    override fun renderLoaded(items: List<StudyProgramCombination>) {
        adapter.submitItems(items)
    }

}
