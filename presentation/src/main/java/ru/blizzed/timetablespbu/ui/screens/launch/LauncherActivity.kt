package ru.blizzed.timetablespbu.ui.screens.launch

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.ui.screens.main.MainActivity

class LauncherActivity : AppCompatActivity() {

    private val viewModel: LauncherViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)

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

    private fun navigateToAuth() = Toast.makeText(this, "Please auth", Toast.LENGTH_SHORT).show()

}
