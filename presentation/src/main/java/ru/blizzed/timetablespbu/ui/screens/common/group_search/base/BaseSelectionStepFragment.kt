package ru.blizzed.timetablespbu.ui.screens.common.group_search.base

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import kotlinx.android.synthetic.main.fragment_base_group_selection_step.loadableContentLayout
import kotlinx.android.synthetic.main.fragment_base_group_selection_step.stepTitle
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.core.navigation.NavigationFragment
import ru.blizzed.timetablespbu.ui.widget.LoadableContentLayout

abstract class BaseSelectionStepFragment<Item, SelectionItem, TViewModel> : NavigationFragment<TViewModel>(
  R.layout.fragment_base_group_selection_step
) where TViewModel : BaseSelectionStepViewModel<Item, SelectionItem> {

  protected val sharedViewModel: SelectionStepsSharedViewModel by sharedViewModel()

  @get:StringRes
  protected abstract val titleRes: Int

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    stepTitle.setText(titleRes)

    viewModel.observeState(this, ::render)
    viewModel.dispatchEvent(ViewEvent.Load())
  }

  protected fun onItemSelected(item: SelectionItem) {
    viewModel.dispatchEvent(ViewEvent.Select(item))
    sharedViewModel.dispatchEvent(SharedEvent.ItemSelected(item))
  }

  protected abstract fun renderLoaded(items: List<Item>)

  private fun render(state: ViewState<Item>) {
    with(state) {
      when {
        isIdle || isLoading -> renderLoading()
        isError             -> renderError()
        items != null       -> renderLoaded(items)
      }
    }
  }

  private fun renderLoading() {
    loadableContentLayout.status = LoadableContentLayout.Status.LOADING
  }

  private fun renderError() {
    loadableContentLayout.status = LoadableContentLayout.Status.ERROR
//    loadableContentLayout.setOnRetryButtonClickListener { viewModel.dispatchEvent(ViewEvent.Load(param.value)) }
  }

}
