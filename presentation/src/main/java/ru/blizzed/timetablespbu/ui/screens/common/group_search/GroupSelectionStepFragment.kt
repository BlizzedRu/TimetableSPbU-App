package ru.blizzed.timetablespbu.ui.screens.common.group_search

import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.domain.entities.AdmissionYear
import ru.blizzed.timetablespbu.domain.entities.Group
import ru.blizzed.timetablespbu.ui.screens.common.group_search.base.BaseSelectionStepAdapter
import ru.blizzed.timetablespbu.ui.screens.common.group_search.base.BaseSelectionStepFragment

class GroupSelectionStepFragment : BaseSelectionStepFragment<Group, Group, AdmissionYear, GroupStepViewModel>() {

    override val viewModel: GroupStepViewModel by viewModel()

    override val titleRes: Int = R.string.welcome_student_group_search_step_group_title

    override val param: Lazy<AdmissionYear>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    private val adapter = BaseSelectionStepAdapter(Group::alias).also {
        it.onItemClickListener = { item, _ ->
            onItemSelected(item)
        }
    }

    override fun renderLoaded(items: List<Group>) {
        adapter.submitItems(items)
    }

}
