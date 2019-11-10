package ru.blizzed.timetablespbu.ui.screens.launcher

import android.content.Intent
import android.os.Bundle
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.core.BaseActivity
import ru.blizzed.timetablespbu.ui.screens.main.MainActivity
import ru.blizzed.timetablespbu.ui.screens.welcome.WelcomeActivity

class LauncherActivity : BaseActivity() {

    private val viewModel: LauncherViewModel by viewModel()

    override val layoutRes: Int = R.layout.activity_launcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.dispatchEvent(ViewEvent.AppStarted)

        viewModel.observeState(this, ::renderState)
    }

    private fun renderState(state: ViewState) {
        with(state) {
            when {
                isError -> finish()
                !isLoading && data != null -> {
                    val isLoggedIn = data

                    if (isLoggedIn) {
                        navigateToMain()
                    } else {
                        navigateToAuth()
                    }
                }
            }
        }
    }

    private fun navigateToMain() = MainActivity.start(this)

    private fun navigateToAuth() = startActivity(Intent(this, WelcomeActivity::class.java))

}
