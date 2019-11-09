package ru.blizzed.timetablespbu.ui.screens.main.search.educators

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.screen_search_modal.*
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.ui.core.*
import ru.blizzed.timetablespbu.ui.widget.LoadableContentLayout
import ru.blizzed.timetablespbu.utils.Event
import ru.blizzed.timetablespbu.viewmodel.EducatorsSearchViewModel
import timber.log.Timber

/**
 * Temp screen
 */
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
                    Timber.e(event.throwable)
                }
            }

            loadableContentLayout.setStatusByEvent(event)
        })

        search.observe(educatorsViewModel::setSearchQuery)
        search.start()
    }

}
