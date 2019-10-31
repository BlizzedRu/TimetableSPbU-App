package ru.blizzed.timetablespbu.ui.screens.faculties

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.screen_search_faculties.*
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.domain.entities.Faculty
import ru.blizzed.timetablespbu.ui.core.InjectViewModel
import ru.blizzed.timetablespbu.ui.core.NoState
import ru.blizzed.timetablespbu.ui.core.ScreenFragment
import ru.blizzed.timetablespbu.ui.core.ScreenParams
import ru.blizzed.timetablespbu.utils.Event
import ru.blizzed.timetablespbu.viewmodel.FacultiesViewModel

@ScreenParams(R.layout.screen_search_faculties)
class FacultiesSearchScreen : ScreenFragment<NoState>() {

    @field:InjectViewModel(FacultiesViewModel::class)
    private lateinit var facultiesViewModel: FacultiesViewModel

    private val facultiesAdapter = FacultiesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        search.observe(facultiesViewModel::setSearchQuery)
        search.start()

        facultiesRecycler.adapter = facultiesAdapter

        facultiesViewModel.faculties.observe(this, Observer(::observeFaculties))
    }

    private fun observeFaculties(event: Event<List<Faculty>>) {
        loadableContentLayout.setStatusByEvent(event)

        when (event) {
            is Event.Success -> facultiesAdapter.submitItems(event.data)
        }
    }

}
