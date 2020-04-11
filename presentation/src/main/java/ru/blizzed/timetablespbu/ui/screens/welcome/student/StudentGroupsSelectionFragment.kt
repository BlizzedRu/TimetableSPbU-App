package ru.blizzed.timetablespbu.ui.screens.welcome.student

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_student_groups_selection.addButton
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.core.BaseFragment
import ru.blizzed.timetablespbu.core.NavigationActivity

class StudentGroupsSelectionFragment : BaseFragment<NavigationActivity>() {

    private val sharedViewModel: GroupSelectionSharedViewModel by sharedViewModel()

    override val layoutRes: Int = R.layout.fragment_student_groups_selection

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addButton.setOnClickListener {
            hostActivity.navigation.navigate(R.id.action_studentGroupsSelection_to_groupSelectionFragment)
        }

        sharedViewModel.group.observe(viewLifecycleOwner, Observer {
//            hostActivity.navigation.navigate(R.id.action_groupSelectionFragment_to_studentGroupsSelection)
        })

    }

}
