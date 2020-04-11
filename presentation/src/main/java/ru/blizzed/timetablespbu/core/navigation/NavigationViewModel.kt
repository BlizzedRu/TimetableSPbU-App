package ru.blizzed.timetablespbu.core.navigation

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import ru.blizzed.timetablespbu.core.SingleLiveEvent

abstract class NavigationViewModel : ViewModel() {

  private val navigationCommands = SingleLiveEvent<NavigationCommand>()

  fun getNavigationCommands(): LiveData<NavigationCommand> = navigationCommands

  fun navigate(direction: NavDirections) {
    navigationCommands.postValue(NavigationCommand.To(direction))
  }

  fun setArguments(bundle: Bundle) {
    observeArguments(bundle)
  }

  protected open fun observeArguments(bundle: Bundle) = Unit

}

