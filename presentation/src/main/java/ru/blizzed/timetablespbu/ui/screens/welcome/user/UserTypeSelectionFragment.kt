package ru.blizzed.timetablespbu.ui.screens.welcome.user

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_user_type_selection.*
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.core.BaseFragment
import ru.blizzed.timetablespbu.core.NavigationActivity
import ru.blizzed.timetablespbu.extensions.setOnRippleClickListener

class UserTypeSelectionFragment : BaseFragment<NavigationActivity>() {

    override val layoutRes: Int = R.layout.fragment_user_type_selection

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        studentButton.setOnRippleClickListener { hostActivity.navigation.navigate(R.id.studentGroupSelection) }

        educatorButton.setOnRippleClickListener { hostActivity.navigation.navigate(R.id.educatorSelfSelection) }
    }

}
