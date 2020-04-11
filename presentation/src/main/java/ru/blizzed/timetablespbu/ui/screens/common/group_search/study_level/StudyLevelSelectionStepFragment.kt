package ru.blizzed.timetablespbu.ui.screens.common.group_search.study_level

import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.domain.entities.StudyLevel
import ru.blizzed.timetablespbu.ui.screens.common.group_search.base.BaseSelectionStepAdapter
import ru.blizzed.timetablespbu.ui.screens.common.group_search.base.BaseSelectionStepFragment

class StudyLevelSelectionStepFragment : BaseSelectionStepFragment<StudyLevel, StudyLevel, StudyLevelStepViewModel>() {

    override val viewModel: StudyLevelStepViewModel by viewModel()

    override val titleRes: Int = R.string.welcome_student_group_search_step_level_title

    private val adapter = BaseSelectionStepAdapter(StudyLevel::name).also {
        it.onItemClickListener = { item, _ ->
            onItemSelected(item)
        }
    }

    override fun renderLoaded(items: List<StudyLevel>) {
        adapter.submitItems(items)
    }

}
