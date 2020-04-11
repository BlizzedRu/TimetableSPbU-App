package ru.blizzed.timetablespbu.ui.screens.common.group_search.base

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import kotlinx.android.synthetic.main.fragment_base_group_selection_step.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.core.BaseActivity
import ru.blizzed.timetablespbu.core.BaseFragment
import ru.blizzed.timetablespbu.ui.widget.LoadableContentLayout

abstract class BaseSelectionStepFragment<Item, SelectionItem, Param, TViewModel : BaseSelectionStepViewModel<Item, SelectionItem, Param>> : BaseFragment<BaseActivity>() {

    protected val sharedViewModel: SelectionStepsSharedViewModel by sharedViewModel()

    protected abstract val viewModel: TViewModel

    @get:StringRes
    protected abstract val titleRes: Int

    protected abstract val param: Lazy<Param>

    override val layoutRes: Int = R.layout.fragment_base_group_selection_step

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        stepTitle.setText(titleRes)

        viewModel.observeState(this, ::render)
        viewModel.dispatchEvent(ViewEvent.Load(param.value))
    }

    protected fun onItemSelected(item: SelectionItem) {
        viewModel.dispatchEvent(ViewEvent.Select(item))
        sharedViewModel.dispatchEvent(SharedEvent.ItemSelected(item))
    }

    protected abstract fun renderLoaded(items: List<Item>)

    protected open fun renderSelected(item: SelectionItem) {
    }

    private fun render(state: ViewState<Item, SelectionItem>) {
        with(state) {
            when {
                isIdle || isLoading -> renderLoading()
                isError -> renderError()
                items != null && selectedItem == null -> renderLoaded(items)
                selectedItem != null -> renderSelected(selectedItem)
            }
        }
    }

    private fun renderLoading() {
        loadableContentLayout.status = LoadableContentLayout.Status.LOADING
    }

    private fun renderError() {
        loadableContentLayout.status = LoadableContentLayout.Status.ERROR
        loadableContentLayout.setOnRetryButtonClickListener { viewModel.dispatchEvent(ViewEvent.Load(param.value)) }
    }

}
