package ru.blizzed.timetablespbu.ui.screens.search.educators

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.screen_educators.*
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.extensions.disableTouchEvents
import ru.blizzed.timetablespbu.extensions.enableTouchEvents
import ru.blizzed.timetablespbu.ui.common.MockAdapter
import ru.blizzed.timetablespbu.ui.core.InjectViewModel
import ru.blizzed.timetablespbu.ui.core.NoState
import ru.blizzed.timetablespbu.ui.core.ScreenFragment
import ru.blizzed.timetablespbu.ui.core.ScreenParams
import ru.blizzed.timetablespbu.ui.widget.LoadableContentLayout.Status
import ru.blizzed.timetablespbu.utils.Event
import ru.blizzed.timetablespbu.viewmodel.EducatorsViewModel

@ScreenParams(R.layout.screen_educators)
class EducatorsScreen : ScreenFragment<NoState>() {

    @field:InjectViewModel(EducatorsViewModel::class)
    private lateinit var viewModel: EducatorsViewModel

    private val favoriteAdapter = CircularEducatorsAdapter()
    private val viewedAdapter = EducatorsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoriteRecycler.adapter = MockAdapter(R.layout.mock_item_list_educator_circular)
        viewedRecycler.adapter = MockAdapter(R.layout.mock_item_list_educator)
        educatorsScroll.disableTouchEvents()

        viewModel.educatorsLoadingProcess.observe(this, Observer { event ->
            when (event) {
                is Event.Success -> {
                    favoriteAdapter.submitItems(event.data.favorites)
                    favoriteRecycler.adapter = favoriteAdapter
                    favoriteLoadableContentLayout.status = if (event.data.favorites.isEmpty()) Status.EMPTY else Status.CONTENT

                    viewedAdapter.submitItems(event.data.viewed)
                    viewedRecycler.adapter = viewedAdapter
                    viewedLoadableContentLayout.status = if (event.data.viewed.isEmpty()) Status.EMPTY else Status.CONTENT

                    educatorsScroll.enableTouchEvents()
                }
            }
        })
    }

}
