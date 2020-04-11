package ru.blizzed.timetablespbu.ui.screens.common.group_search

import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.domain.entities.Faculty
import ru.blizzed.timetablespbu.ui.screens.common.faculty_search.FacultiesAdapter
import ru.blizzed.timetablespbu.ui.screens.common.group_search.base.BaseSelectionStepFragment

class FacultySelectionStepFragment : BaseSelectionStepFragment<Faculty, Faculty, Any, FacultyStepViewModel>() {

    override val viewModel: FacultyStepViewModel by viewModel()

    override val titleRes: Int = R.string.welcome_student_group_search_step_faculty_title

    override val param = lazy { Any() }

    private val adapter = FacultiesAdapter().also {
        it.onItemClickListener = { faculty, _ -> onItemSelected(faculty) }
    }

    override fun renderLoaded(items: List<Faculty>) {
        adapter.submitItems(items)
    }

}
