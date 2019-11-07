package ru.blizzed.timetablespbu.ui.screens.faculties

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.screen_search_faculties.*
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.domain.entities.Faculty
import ru.blizzed.timetablespbu.ui.widget.LoadableContentLayout
import ru.blizzed.timetablespbu.viewmodel.system.ViewModelsProvider

class FacultiesSearchFragment : Fragment() {

    lateinit var viewModel: FacultiesSearchViewModel

    private val facultiesAdapter = FacultiesAdapter().also {
        it.onItemClickListener = { item, _ ->
            viewModel.dispatchEvent(Event.Select(item))
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        viewModel = ViewModelsProvider.of(this).get(FacultiesSearchViewModel::class.java)
        viewModel.observeState(this, ::render)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.screen_search_faculties, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        facultiesRecycler.adapter = facultiesAdapter
        search.observe { query -> viewModel.dispatchEvent(Event.Search(query)) }

        viewModel.dispatchEvent(Event.Load)
    }

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

    private fun renderSelected(faculty: Faculty) {
        loadableContentLayout.status = LoadableContentLayout.Status.CONTENT
        Snackbar.make(view!!, faculty.name, Snackbar.LENGTH_SHORT).show()
    }
}