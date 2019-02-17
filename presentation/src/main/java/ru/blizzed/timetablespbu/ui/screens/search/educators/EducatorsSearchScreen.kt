package ru.blizzed.timetablespbu.ui.screens.search.educators

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.screen_search_educators.educatorsLoadableContentLayout
import kotlinx.android.synthetic.main.screen_search_educators.educatorsSearchRecycler
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.ui.core.InjectViewModel
import ru.blizzed.timetablespbu.ui.core.NoState
import ru.blizzed.timetablespbu.ui.core.ScreenFragment
import ru.blizzed.timetablespbu.ui.core.ScreenParams
import ru.blizzed.timetablespbu.ui.widget.LoadableContentLayout
import ru.blizzed.timetablespbu.utils.Event
import ru.blizzed.timetablespbu.viewmodel.EducatorSearchViewModel

@ScreenParams(R.layout.screen_search_educators)
class EducatorsSearchScreen : ScreenFragment<NoState>() {

    @field:InjectViewModel(EducatorSearchViewModel::class)
    private lateinit var viewModel: EducatorSearchViewModel

    private var educatorsAdapter = EducatorsSearchAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.educatorsSearchProcess.observe(this, Observer { event ->
            when (event) {
                is Event.Success -> {
                    educatorsAdapter.submitItems(event.data)
                    if (event.data.isEmpty()) {
                        educatorsLoadableContentLayout.status = LoadableContentLayout.Status.EMPTY
                    } else {
                        educatorsLoadableContentLayout.status = LoadableContentLayout.Status.CONTENT
                    }
                }
            }

            educatorsLoadableContentLayout.setStatusByEvent(event)
        })

        educatorsSearchRecycler.adapter = educatorsAdapter
    }

}