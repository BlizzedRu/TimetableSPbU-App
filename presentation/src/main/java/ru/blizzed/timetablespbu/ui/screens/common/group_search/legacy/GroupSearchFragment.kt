package ru.blizzed.timetablespbu.ui.screens.common.group_search.legacy

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.common_search_screen_layout.recycler
import kotlinx.android.synthetic.main.fragment_base_group_selection_step.*
import kotlinx.android.synthetic.main.screen_educators.loadableContentLayout
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.core.BaseFragment
import ru.blizzed.timetablespbu.core.NavigationActivity
import ru.blizzed.timetablespbu.domain.entities.Group
import ru.blizzed.timetablespbu.domain.entities.StudyLevel
import ru.blizzed.timetablespbu.domain.entities.StudyProgramCombination
import ru.blizzed.timetablespbu.ui.screens.common.group_search.AdmissionYearAdapter
import ru.blizzed.timetablespbu.ui.screens.common.group_search.StudyLevelAdapter
import ru.blizzed.timetablespbu.ui.screens.welcome.student.GroupSelectionSharedViewModel
import ru.blizzed.timetablespbu.ui.widget.LoadableContentLayout

class GroupSearchFragment : BaseFragment<NavigationActivity>() {

    private val sharedViewModel: GroupSelectionSharedViewModel by sharedViewModel()
    private val viewModel: GroupSearchViewModel by viewModel()

    override val layoutRes: Int = R.layout.fragment_base_group_selection_step

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.group.observe(this, Observer {
            hostActivity.navigation.navigate(R.id.action_groupSearch_to_studentGroupsSelection)
        })

        viewModel.dispatchEvent(ViewEvent.Load(sharedViewModel.faculty.value!!.alias)) // TODO with navigation bundle
        viewModel.observeState(this, ::renderState)
    }

    private fun renderState(state: ViewState) {
        with(state) {
            when {
                isIdle || isLoading -> renderLoading()
                isError -> renderError()
                step is Step.Levels -> renderLevels(step.levels)
                step is Step.ProgramCombinations -> renderProgramCombinations(step.combinations)
                step is Step.Groups -> renderGroups(step.groups)
            }
        }
        renderStepTitleByState(state)
    }

    private fun renderLoading() {
        loadableContentLayout.status = LoadableContentLayout.Status.LOADING
    }

    private fun renderError() {
        loadableContentLayout.status = LoadableContentLayout.Status.ERROR
    }

    private fun renderLevels(levels: List<StudyLevel>) {
        loadableContentLayout.status = LoadableContentLayout.Status.CONTENT

        recycler.adapter = StudyLevelAdapter().apply {
            submitItems(levels)
            onItemClickListener = { level, _ ->
                viewModel.dispatchEvent(ViewEvent.StudyLevelSelected(level))
            }
        }
    }

    private fun renderProgramCombinations(combinations: List<StudyProgramCombination>) {
        loadableContentLayout.status = LoadableContentLayout.Status.CONTENT

        recycler.adapter = AdmissionYearAdapter().apply {
            submitItems(combinations)
            onItemClickListener = { combination, _ ->
            }
        }
    }

    private fun renderGroups(groups: List<Group>) {
        loadableContentLayout.status = LoadableContentLayout.Status.CONTENT
        // TODO make bottom sheet
    }

    private fun renderStepTitleByState(state: ViewState) {
        stepTitle.text = getString(when (state.step) {
            is Step.Levels -> R.string.welcome_student_group_search_step_level_title
            is Step.ProgramCombinations -> R.string.welcome_student_group_search_step_program_combination_title
            is Step.Groups -> R.string.welcome_student_group_search_step_group_title
        })
    }
}
