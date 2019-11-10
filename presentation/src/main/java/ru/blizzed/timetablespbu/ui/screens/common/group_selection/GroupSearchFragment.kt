package ru.blizzed.timetablespbu.ui.screens.common.group_selection

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.core.BaseFragment
import ru.blizzed.timetablespbu.core.NavigationActivity
import ru.blizzed.timetablespbu.ui.screens.welcome.student.GroupSelectionSharedViewModel

class GroupSearchFragment : BaseFragment<NavigationActivity>() {

    private val sharedViewModel: GroupSelectionSharedViewModel by sharedViewModel()

    override val layoutRes: Int = R.layout.fragment_common_search_group

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.group.observe(this, Observer {
            hostActivity.navigation.navigate(R.id.action_groupSearch_to_studentGroupsSelection)
        })

    }

}
