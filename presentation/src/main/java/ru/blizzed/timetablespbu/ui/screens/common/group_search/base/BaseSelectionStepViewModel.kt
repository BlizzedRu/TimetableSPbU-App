package ru.blizzed.timetablespbu.ui.screens.common.group_search.base

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.rxkotlin.ofType
import ru.blizzed.timetablespbu.core.mvi.MviViewModel
import ru.blizzed.timetablespbu.core.mvi.Reducer

abstract class BaseSelectionStepViewModel<Item, SelectionItem> :
  MviViewModel<ViewState<Item>, ViewEvent, StateChange<Item>>() {

  override val initialState: ViewState<Item> = ViewState(isIdle = true)

  override val stateReducer: Reducer<ViewState<Item>, StateChange<Item>> = { state, change ->
      when (change) {
        is StateChange.Loading   -> state.copy(isIdle = false, isLoading = true, isError = false)
        is StateChange.Error     -> state.copy(isIdle = false, isLoading = false, isError = true)
        is StateChange.Loaded-> state.copy(
          isIdle = false,
          isLoading = false,
          isError = false,
          items = change.items
        )
      }
    }

  override fun subscribeOnViewEvents(
    events: Observable<ViewEvent>
  ): Observable<StateChange<Item>> {
    val loadEvent = events.ofType<ViewEvent.Load>()
      .flatMapSingle {
        loadItems()
          .map<StateChange<Item>> { StateChange.Loaded(it) }
          .onErrorReturn { StateChange.Error(it) }
      }
      .startWith(StateChange.Loading())

    events.ofType<ViewEvent.Select<SelectionItem>>()
      .doOnNext { event -> onItemSelected(event.item) }
      .subscribe()

    return loadEvent.startWith(StateChange.Loading())
  }

  protected abstract fun onItemSelected(item: SelectionItem)

  protected abstract fun loadItems(): Single<List<Item>>

}
