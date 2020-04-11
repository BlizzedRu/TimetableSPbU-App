package ru.blizzed.timetablespbu.domain.repositories

import io.reactivex.Single
import ru.blizzed.timetablespbu.domain.entities.FacultyAlias
import ru.blizzed.timetablespbu.domain.entities.Group
import ru.blizzed.timetablespbu.domain.entities.StudyLevel

interface GroupSearchRepository {

    fun getStudyLevelsByDivisionAlias(facultyAlias: FacultyAlias): Single<List<StudyLevel>>

    fun getGroupsByProgramId(programId: Int): Single<List<Group>>

}
