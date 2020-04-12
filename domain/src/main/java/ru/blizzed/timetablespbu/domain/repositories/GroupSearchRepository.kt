package ru.blizzed.timetablespbu.domain.repositories

import io.reactivex.Single
import ru.blizzed.timetablespbu.domain.entities.FacultyAlias
import ru.blizzed.timetablespbu.domain.entities.Group
import ru.blizzed.timetablespbu.domain.entities.StudyLevel
import ru.blizzed.timetablespbu.domain.entities.StudyProgramCombination

interface GroupSearchRepository {

    fun getStudyLevelsByDivisionAlias(facultyAlias: FacultyAlias): Single<List<StudyLevel>>

    fun getProgramCombinations(facultyAlias: String, studyLevelId: Int): Single<List<StudyProgramCombination>>

    fun getGroupsByProgramId(programId: Int): Single<List<Group>>

}
