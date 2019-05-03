package ru.blizzed.timetablespbu.ui.screens.educators

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.screen_search_modal.loadableContentLayout
import kotlinx.android.synthetic.main.screen_search_modal.recycler
import kotlinx.android.synthetic.main.screen_search_modal.search
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.ui.core.InjectViewModel
import ru.blizzed.timetablespbu.ui.core.LifecycleOwnerType
import ru.blizzed.timetablespbu.ui.core.NoState
import ru.blizzed.timetablespbu.ui.core.ScreenFragment
import ru.blizzed.timetablespbu.ui.core.ScreenParams
import ru.blizzed.timetablespbu.ui.widget.LoadableContentLayout
import ru.blizzed.timetablespbu.utils.Event
import ru.blizzed.timetablespbu.viewmodel.EducatorsSearchViewModel

@ScreenParams(R.layout.screen_search_modal)
class EducatorsSearchScreen : ScreenFragment<NoState>() {

    @field:InjectViewModel(EducatorsSearchViewModel::class, lifecycleOwner = LifecycleOwnerType.ACTIVITY)
    private lateinit var educatorsViewModel: EducatorsSearchViewModel

    private var viewedEducatorsAdapter = EducatorsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler.adapter = viewedEducatorsAdapter.apply {
            onEducatorClickListener = educatorsViewModel::setViewed

            onFavoriteClickListener = {
                educatorsViewModel.setFavorite(it, !it.isFavorite)
            }
        }

        educatorsViewModel.educatorsSearchProcess.observe(this, Observer { event ->
            when (event) {
                is Event.Success -> {
                    viewedEducatorsAdapter.submitItems(event.data)
                    if (event.data.isEmpty()) {
                        loadableContentLayout.status = LoadableContentLayout.Status.EMPTY
                    } else {
                        loadableContentLayout.status = LoadableContentLayout.Status.CONTENT
                    }
                }
                is Event.Error -> {
                    event.throwable.printStackTrace()
                }
            }

            loadableContentLayout.setStatusByEvent(event)
        })

        search.observe(educatorsViewModel::setSearchQuery)
        search.start()
    }

}
