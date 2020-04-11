package ru.blizzed.timetablespbu.ui.screens.common.group_search.legacy

import ru.blizzed.timetablespbu.domain.entities.AdmissionYear
import ru.blizzed.timetablespbu.domain.entities.Group
import ru.blizzed.timetablespbu.domain.entities.StudyLevel

sealed class ViewEvent {

    class Load(val divisionAlias: String) : ViewEvent()

    class Search(val query: String) : ViewEvent()

    class StudyLevelSelected(val studyLevel: StudyLevel) : ViewEvent()

    class AdmissionYearSelected(val admissionYear: AdmissionYear) : ViewEvent()

    class GroupSelected(val group: Group) : ViewEvent()

}
