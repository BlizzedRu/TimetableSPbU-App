package ru.blizzed.timetablespbu.ui.screens.common.group_search

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.rxkotlin.ofType
import ru.blizzed.timetablespbu.core.mvi.MviViewModel
import ru.blizzed.timetablespbu.core.mvi.Reducer
import ru.blizzed.timetablespbu.domain.usecases.timetable_browse.ObserveSelectedGroupsUseCase
import ru.blizzed.timetablespbu.domain.usecases.timetable_browse.RemoveGroupUseCase

class TimetableBrowseViewModel(
  private val removeGroup: RemoveGroupUseCase,
  private val observeSelectedGroups: ObserveSelectedGroupsUseCase
) : MviViewModel<ViewState, ViewEvent, StateChange>() {

  override val initialState: ViewState = ViewState(selectedGroupsVisible = false, selectedGroups = listOf())

  override val stateReducer: Reducer<ViewState, StateChange> = { _, change ->
    when (change) {
      is StateChange.UpdateGroups -> ViewState(
        selectedGroupsVisible = change.groups.isNotEmpty(),
        selectedGroups = change.groups
      )
    }
  }

  init {
    onInitialized()
  }

  override fun subscribeOnViewEvents(events: Observable<ViewEvent>): Observable<StateChange> {
    events.ofType<ViewEvent.OnGroupRemoveClicked>()
      .flatMapCompletable { event ->
        Completable.fromAction { removeGroup(event.group) }
      }
      .subscribe()

    return observeSelectedGroups().map(StateChange::UpdateGroups)
  }
}
