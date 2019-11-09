package ru.blizzed.timetablespbu.ui.screens.launch

import io.reactivex.Observable
import io.reactivex.rxkotlin.ofType
import ru.blizzed.timetablespbu.domain.usecases.UserInfoUseCase
import ru.blizzed.timetablespbu.mvi.MviViewModel
import ru.blizzed.timetablespbu.mvi.Reducer
import ru.blizzed.timetablespbu.mvi.SingleDataStateChange
import ru.blizzed.timetablespbu.mvi.SingleDataViewState

typealias ViewState = SingleDataViewState<Boolean>
typealias StateChange = SingleDataStateChange<Boolean>
typealias LoadingStateChange = SingleDataStateChange.Loading<Boolean>
typealias LoadedStateChange = SingleDataStateChange.Loaded<Boolean>
typealias ErrorStateChange = SingleDataStateChange.Error<Boolean>

class LauncherViewModel(
        private val userInfoUseCase: UserInfoUseCase
) : MviViewModel<ViewState, ViewEvent, StateChange>() {

    override val initialState = ViewState(isLoading = true)

    override val stateReducer: Reducer<ViewState, StateChange> = { state, change ->
        when (change) {
            is LoadingStateChange -> state.copy(isLoading = true, isError = false, data = change.lastData)
            is LoadedStateChange -> state.copy(isLoading = false, isError = false, data = change.data)
            is ErrorStateChange -> state.copy(isLoading = false, isError = true, data = change.lastData)
        }
    }

    init {
        onInitialized()
    }

    override fun subscribeOnViewEvents(events: Observable<ViewEvent>): Observable<StateChange> {
        return events.ofType<ViewEvent.AppStarted>()
                .flatMapSingle {
                    userInfoUseCase.isLoggedIn()
                            .map<StateChange>(::LoadedStateChange)
                            .onErrorReturn { ErrorStateChange(it) }
                }
                .startWith(LoadingStateChange())
    }

}

sealed class ViewEvent {
    object AppStarted : ViewEvent()
}

