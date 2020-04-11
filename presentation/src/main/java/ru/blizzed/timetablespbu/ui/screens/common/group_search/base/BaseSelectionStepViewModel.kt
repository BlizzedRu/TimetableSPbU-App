package ru.blizzed.timetablespbu.ui.screens.common.group_search.base

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.rxkotlin.ofType
import ru.blizzed.timetablespbu.core.mvi.MviViewModel
import ru.blizzed.timetablespbu.core.mvi.Reducer

abstract class BaseSelectionStepViewModel<Item, SelectionItem, Param> :
  MviViewModel<ViewState<Item>, ViewEvent<Item, SelectionItem, Param>, StateChange<Item, SelectionItem>>() {

  override val initialState: ViewState<Item> = ViewState(isIdle = true)

  override val stateReducer: Reducer<ViewState<Item>, StateChange<Item, SelectionItem>> = { state, change ->
      when (change) {
        is StateChange.Loading -> state.copy(isIdle = false, isLoading = true, isError = false)
        is StateChange.Error -> state.copy(isIdle = false, isLoading = false, isError = true)
        is StateChange.Loaded -> state.copy(
          isIdle = false,
          isLoading = false,
          isError = false,
          items = change.items
        )
        is StateChange.Selected -> state.copy(
          isIdle = false,
          isLoading = false,
          isError = false
        )
      }
    }

  override fun subscribeOnViewEvents(
    events: Observable<ViewEvent<Item, SelectionItem, Param>>
  ): Observable<StateChange<Item, SelectionItem>> {
    val loadEvent = events.ofType<ViewEvent.Load<Item, SelectionItem, Param>>()
      .flatMapSingle { event ->
        loadItems(event.param)
          .map<StateChange<Item, SelectionItem>> { StateChange.Loaded(it) }
          .onErrorReturn { StateChange.Error(it) }
      }
      .startWith(StateChange.Loading())

    val selectEvent = events.ofType<ViewEvent.Select<Item, SelectionItem, Param>>()
      .flatMapSingle { event -> Single.just(StateChange.Selected<Item, SelectionItem>(event.item)) }

    return Observable.merge(loadEvent, selectEvent)
      .startWith(StateChange.Loading())
  }

  protected abstract fun loadItems(param: Param): Single<List<Item>>

}
