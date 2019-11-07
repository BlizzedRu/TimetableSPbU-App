package ru.blizzed.timetablespbu.ui.screens.faculties

import io.reactivex.Observable
import io.reactivex.rxkotlin.ofType
import ru.blizzed.timetablespbu.domain.repositories.FacultiesRepository
import ru.blizzed.timetablespbu.mvi.MviViewModel
import ru.blizzed.timetablespbu.mvi.Reducer
import javax.inject.Inject

class FacultiesSearchViewModel @Inject constructor(
        private val facultiesRepository: FacultiesRepository
) : MviViewModel<ViewState, Event, Change>() {

    override val initialState: ViewState = ViewState(isIdle = true)

    override val stateReducer: Reducer<ViewState, Change> = { oldState, change ->
        when (change) {
            is Change.Loading -> oldState.copy(isIdle = false, isLoading = true, isError = false)
            is Change.Loaded -> oldState.copy(isIdle = false, isLoading = false, isError = false, faculties = change.faculties)
            is Change.Error -> oldState.copy(isIdle = false, isLoading = false, isError = true, error = change.error)
        }
    }

    init {
        onInitialized()
    }

    override fun bindEvents(events: Observable<Event>): Observable<Change> {
        val loadChange = events.ofType<Event.Load>()
                .flatMapSingle {
                    facultiesRepository.getAll()
                            .map<Change>(Change::Loaded)
                            .onErrorReturn(Change::Error)
                }

        val searchChange = events.ofType<Event.Search>()
                .map(Event.Search::query)
                .flatMapSingle { query ->
                    facultiesRepository.search(query)
                            .map<Change>(Change::Loaded)
                            .onErrorReturn(Change::Error)
                }

        val selectChange = events.ofType<Event.Select>()
                .map<Change> { Change.Loading }
                .flatMap { Observable.error<Change>(Throwable()).onErrorReturn(Change::Error) }


        return Observable.merge(loadChange, searchChange, selectChange)
                .startWith(Change.Loading)
    }
}
