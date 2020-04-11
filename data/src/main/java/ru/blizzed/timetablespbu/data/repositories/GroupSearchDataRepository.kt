package ru.blizzed.timetablespbu.data.repositories

import io.reactivex.Single
import ru.blizzed.timetablespbu.data.datasources.DivisionsRemoteDataSource
import ru.blizzed.timetablespbu.domain.entities.Group
import ru.blizzed.timetablespbu.domain.entities.StudyLevel
import ru.blizzed.timetablespbu.domain.repositories.GroupSearchRepository

class GroupSearchDataRepository(private val divisionsRemoteDataSource: DivisionsRemoteDataSource) : GroupSearchRepository {

    override fun getStudyLevelsByDivisionAlias(divisionAlias: String): Single<List<StudyLevel>> = divisionsRemoteDataSource.getStudyLevels(divisionAlias)

    override fun getGroupsByProgramId(programId: Int): Single<List<Group>> = divisionsRemoteDataSource.getProgramGroups(programId)

}
