package ru.blizzed.timetablespbu.ui.screens.common.group_search.legacy

import ru.blizzed.timetablespbu.domain.entities.Group
import ru.blizzed.timetablespbu.domain.entities.StudyLevel
import ru.blizzed.timetablespbu.domain.entities.StudyProgramCombination

sealed class StateChange {
    object Loading : StateChange()

    class Error(val error: Throwable) : StateChange()

    class LevelsLoaded(val levels: List<StudyLevel>) : StateChange()
    class ProgramCombinationsLoaded(val combinations: List<StudyProgramCombination>) : StateChange()
    class GroupsLoaded(val groups: List<Group>) : StateChange()

}
