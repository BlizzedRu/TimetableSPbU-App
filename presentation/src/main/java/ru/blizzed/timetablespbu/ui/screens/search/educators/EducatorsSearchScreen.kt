package ru.blizzed.timetablespbu.ui.screens.search.educators

import android.widget.Button
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.ui.common.LoadableContentLayout
import ru.blizzed.timetablespbu.ui.core.*
import ru.blizzed.timetablespbu.utils.Event
import ru.blizzed.timetablespbu.viewmodel.SearchViewModel

@ScreenParams(R.layout.screen_search_educators)
class EducatorsSearchScreen(screenContext: ScreenContext) : Screen<NoState>(screenContext) {

    @field:InjectViewModel(SearchViewModel::class, lifecycleOwner = LifecycleOwnerType.PARENT)
    private lateinit var viewModel: SearchViewModel

    private val loadableLayout: LoadableContentLayout = view as LoadableContentLayout
    private val educatorsRecycler: RecyclerView = findViewById(R.id.educatorsSearchRecycler)
    private var educatorsAdapter = EducatorsSearchAdapter()
    private val retryButton: Button = findViewById(R.id.retryButton)

    init {
        viewModel.searchProcess.observe(this, Observer { event ->
            when (event) {
                is Event.Loading -> loadableLayout.status = LoadableContentLayout.Status.LOADING
                is Event.Error -> loadableLayout.status = LoadableContentLayout.Status.ERROR
                is Event.Success -> {
                    educatorsAdapter.aducators = event.data
                    if (event.data.isEmpty()) {
                        loadableLayout.status = LoadableContentLayout.Status.EMPTY
                    } else {
                        loadableLayout.status = LoadableContentLayout.Status.CONTENT
                    }
                }
            }
        })

        educatorsRecycler.adapter = educatorsAdapter
        educatorsRecycler.layoutManager = LinearLayoutManager(activity)
    }

}