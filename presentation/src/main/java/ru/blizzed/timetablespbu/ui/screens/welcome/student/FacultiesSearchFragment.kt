package ru.blizzed.timetablespbu.ui.screens.welcome.student

import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import ru.blizzed.timetablespbu.domain.entities.Faculty
import ru.blizzed.timetablespbu.ui.screens.common.faculty_search.BaseFacultiesSearchFragment

class FacultiesSearchFragment : BaseFacultiesSearchFragment() {

    private val sharedViewModel: GroupSelectionSharedViewModel by sharedViewModel()

    override fun onFacultySelected(faculty: Faculty) {
        sharedViewModel.dispatchEvent(ViewEvent.FacultySelected(faculty))
//        hostActivity.navigation.navigate(R.id.action_facultiesSearch_to_groupSearch)
    }

}
