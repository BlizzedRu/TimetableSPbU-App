package ru.blizzed.timetablespbu.ui.screens.common.faculty_search

import android.content.Context
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.common_search_appbar_layout.*
import kotlinx.android.synthetic.main.common_search_screen_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.core.BaseFragment
import ru.blizzed.timetablespbu.core.NavigationActivity
import ru.blizzed.timetablespbu.domain.entities.Faculty
import ru.blizzed.timetablespbu.ui.widget.LoadableContentLayout

abstract class BaseFacultiesSearchFragment : BaseFragment<NavigationActivity>() {

    private val viewModel: FacultiesSearchViewModel by viewModel()

    private val facultiesAdapter by lazy {
        FacultiesAdapter().also {
            it.onItemClickListener = { faculty, _ ->
                onFacultySelected(faculty)
            }
        }
    }

    override val layoutRes: Int = R.layout.common_search_screen_layout

    override fun onAttach(context: Context) {
        super.onAttach(context)

        viewModel.observeState(this, ::render)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler.adapter = facultiesAdapter
        search.observe { query -> viewModel.dispatchEvent(ViewEvent.Search(query)) }

        viewModel.dispatchEvent(ViewEvent.Load)
    }

    abstract fun onFacultySelected(faculty: Faculty)

    private fun render(state: ViewState) {
        with(state) {
            when {
                isIdle || isLoading -> renderLoading()
                isError -> renderError(error?.toString().orEmpty())
                else -> {
                    if (faculties.isEmpty()) renderEmpty() else renderLoaded(faculties)
                }
            }
        }
    }

    private fun renderError(error: String) {
        loadableContentLayout.status = LoadableContentLayout.Status.ERROR
    }

    private fun renderLoading() {
        loadableContentLayout.status = LoadableContentLayout.Status.LOADING
    }

    private fun renderEmpty() {
        loadableContentLayout.status = LoadableContentLayout.Status.EMPTY
    }

    private fun renderLoaded(faculties: List<Faculty>) {
        loadableContentLayout.status = LoadableContentLayout.Status.CONTENT
        facultiesAdapter.submitItems(faculties)
    }
}
