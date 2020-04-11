package ru.blizzed.timetablespbu.ui.screens.common.group_search

import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.domain.entities.Faculty
import ru.blizzed.timetablespbu.domain.entities.StudyLevel
import ru.blizzed.timetablespbu.ui.screens.common.group_search.base.BaseSelectionStepAdapter
import ru.blizzed.timetablespbu.ui.screens.common.group_search.base.BaseSelectionStepFragment

class StudyLevelSelectionStepFragment : BaseSelectionStepFragment<StudyLevel, StudyLevel, Faculty, StudyLevelStepViewModel>() {

    override val viewModel: StudyLevelStepViewModel by viewModel()

    override val titleRes: Int = R.string.welcome_student_group_search_step_level_title

    override val param: Lazy<Faculty>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    private val adapter = BaseSelectionStepAdapter(StudyLevel::name).also {
        it.onItemClickListener = { item, _ ->
            onItemSelected(item)
        }
    }

    override fun renderLoaded(items: List<StudyLevel>) {
        adapter.submitItems(items)
    }

}
