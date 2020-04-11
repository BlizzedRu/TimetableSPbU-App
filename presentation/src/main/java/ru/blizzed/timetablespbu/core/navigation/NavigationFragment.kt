package ru.blizzed.timetablespbu.core.navigation

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import ru.blizzed.timetablespbu.core.navigation.NavigationCommand.Back
import ru.blizzed.timetablespbu.core.navigation.NavigationCommand.BackTo
import ru.blizzed.timetablespbu.core.navigation.NavigationCommand.To

abstract class NavigationFragment<ViewModelType : NavigationViewModel>(@LayoutRes layoutRes: Int) : Fragment(layoutRes) {

  protected abstract val viewModel: ViewModelType

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    viewModel.getNavigationCommands().observe(viewLifecycleOwner, Observer { command ->
      val navController = findNavController()
      when (command) {
        is To     -> {
          navController.navigate(command.direction)
        }
        is Back   -> {
          navController.popBackStack()
        }
        is BackTo -> {
          navController.popBackStack(command.directionId, true)
        }
      }
    })
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    if (savedInstanceState == null) {
      arguments?.let(viewModel::setArguments)
    }
  }
}
