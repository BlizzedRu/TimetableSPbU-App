package ru.blizzed.timetablespbu.ui.screens.main.search.faculties

import io.reactivex.Observable
import io.reactivex.rxkotlin.ofType
import ru.blizzed.timetablespbu.domain.repositories.FacultiesRepository
import ru.blizzed.timetablespbu.mvi.MviViewModel
import ru.blizzed.timetablespbu.mvi.Reducer

class FacultiesSearchViewModel(
        private val facultiesRepository: FacultiesRepository
) : MviViewModel<ViewState, ViewEvent, StateChange>() {

    override val initialState: ViewState = ViewState(isIdle = true)

    override val stateReducer: Reducer<ViewState, StateChange> = { oldState, change ->
        when (change) {
            is StateChange.Loading -> oldState.copy(
                    isIdle = false,
                    isLoading = true,
                    isError = false
            )
            is StateChange.Loaded -> oldState.copy(
                    isIdle = false,
                    isLoading = false,
                    isError = false,
                    faculties = change.faculties
            )
            is StateChange.Error -> oldState.copy(
                    isIdle = false,
                    isLoading = false,
                    isError = true,
                    error = change.error
            )
        }
    }

    init {
        onInitialized()
    }

    override fun subscribeOnViewEvents(events: Observable<ViewEvent>): Observable<StateChange> {
        val loadChange = events.ofType<ViewEvent.Load>()
                .flatMapSingle {
                    facultiesRepository.getAll()
                            .map<StateChange>(StateChange::Loaded)
                            .onErrorReturn(StateChange::Error)
                }

        val searchChange = events.ofType<ViewEvent.Search>()
                .map(ViewEvent.Search::query)
                .flatMapSingle { query ->
                    facultiesRepository.search(query)
                            .map<StateChange>(StateChange::Loaded)
                            .onErrorReturn(StateChange::Error)
                }

        val selectChange = events.ofType<ViewEvent.Select>()
                .map<StateChange> { StateChange.Loading }
                .flatMap {
                    Observable.error<StateChange>(Throwable()).onErrorReturn(StateChange::Error)
                }


        return Observable.merge(loadChange, searchChange, selectChange)
                .startWith(StateChange.Loading)
    }
}
