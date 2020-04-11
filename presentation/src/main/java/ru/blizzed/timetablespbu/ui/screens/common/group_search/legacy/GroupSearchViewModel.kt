package ru.blizzed.timetablespbu.ui.screens.common.group_search.legacy

import io.reactivex.Observable
import io.reactivex.rxkotlin.ofType
import ru.blizzed.timetablespbu.core.mvi.MviViewModel
import ru.blizzed.timetablespbu.core.mvi.Reducer
import ru.blizzed.timetablespbu.domain.entities.Group
import ru.blizzed.timetablespbu.domain.entities.StudyLevel
import ru.blizzed.timetablespbu.domain.entities.StudyProgramCombination
import ru.blizzed.timetablespbu.domain.usecases.GroupSearchUseCase

class GroupSearchViewModel(private val groupSearchUseCase: GroupSearchUseCase) : MviViewModel<ViewState, ViewEvent, StateChange>() {

    override val initialState: ViewState = ViewState(isIdle = true, step = Step.Levels())

    override val stateReducer: Reducer<ViewState, StateChange> = { state, change ->
        when (change) {
            is StateChange.Loading -> state.copy(isIdle = false, isLoading = true, isError = false)
            is StateChange.Error -> state.copy(isIdle = false, isLoading = false, isError = true)
            is StateChange.LevelsLoaded -> state.copy(isIdle = false, isLoading = false, isError = false, step = Step.Levels(change.levels))
            is StateChange.ProgramCombinationsLoaded -> state.copy(isIdle = false, isLoading = false, isError = false, step = Step.ProgramCombinations(change.combinations))
            is StateChange.GroupsLoaded -> state.copy(isIdle = false, isLoading = false, isError = false, step = Step.Groups(change.groups))
        }
    }

    init {
        onInitialized()
    }

    override fun subscribeOnViewEvents(events: Observable<ViewEvent>): Observable<StateChange> {
        val loadChange = events.ofType<ViewEvent.Load>()
                .flatMapSingle { loadEvent ->
                    groupSearchUseCase
                            .getStudyLevelsByDivisionAlias(loadEvent.divisionAlias)
                            .map<StateChange>(StateChange::LevelsLoaded)
                            .onErrorReturn(StateChange::Error)
                }
                .startWith(StateChange.Loading)

        val studyLevelSelectedChange = events.ofType<ViewEvent.StudyLevelSelected>()
                .flatMapSingle { event ->
                    groupSearchUseCase
                            .getStudyProgramCombinationsByLevel(event.studyLevel)
                            .map<StateChange>(StateChange::ProgramCombinationsLoaded)
                            .onErrorReturn(StateChange::Error)
                }
                .startWith(StateChange.Loading)


        val admissionYearSelectedChange = events.ofType<ViewEvent.AdmissionYearSelected>()
                .flatMapSingle { event ->
                    groupSearchUseCase
                            .getGroupsByAdmissionYear(event.admissionYear)
                            .map<StateChange>(StateChange::GroupsLoaded)
                            .onErrorReturn(StateChange::Error)
                }
                .startWith(StateChange.Loading)

        return Observable.merge(loadChange, studyLevelSelectedChange, admissionYearSelectedChange)
                .startWith(StateChange.Loading)
    }
}

sealed class Step {
    class Levels(val levels: List<StudyLevel> = emptyList()) : Step()

    class ProgramCombinations(val combinations: List<StudyProgramCombination> = emptyList()) : Step()

    class Groups(val groups: List<Group> = emptyList()) : Step()
}
