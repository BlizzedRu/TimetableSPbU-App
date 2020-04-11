package ru.blizzed.timetablespbu.domain.repositories

import io.reactivex.Single
import ru.blizzed.timetablespbu.domain.entities.Group
import ru.blizzed.timetablespbu.domain.entities.StudyLevel

interface GroupSearchRepository {

    fun getStudyLevelsByDivisionAlias(divisionAlias: String): Single<List<StudyLevel>>

    fun getGroupsByProgramId(programId: Int): Single<List<Group>>

}
