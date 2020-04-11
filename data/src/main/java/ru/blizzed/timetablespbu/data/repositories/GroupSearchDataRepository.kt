package ru.blizzed.timetablespbu.data.repositories

import io.reactivex.Single
import ru.blizzed.timetablespbu.data.datasources.DivisionsLocalDataSource
import ru.blizzed.timetablespbu.data.datasources.DivisionsRemoteDataSource
import ru.blizzed.timetablespbu.domain.entities.Group
import ru.blizzed.timetablespbu.domain.entities.StudyLevel
import ru.blizzed.timetablespbu.domain.repositories.GroupSearchRepository

class GroupSearchDataRepository(
  private val divisionsRemoteDataSource: DivisionsRemoteDataSource,
  private val divisionsLocalDataSource: DivisionsLocalDataSource
) : GroupSearchRepository {

  override fun getStudyLevelsByDivisionAlias(facultyAlias: String): Single<List<StudyLevel>> =
    divisionsLocalDataSource.getOrNull(facultyAlias)
      ?.let { Single.just(it) }
      ?: divisionsRemoteDataSource.getStudyLevels(facultyAlias)
        .doOnSuccess { levels -> divisionsLocalDataSource.put(facultyAlias, levels) }

  override fun getGroupsByProgramId(programId: Int): Single<List<Group>> =
    divisionsRemoteDataSource.getProgramGroups(programId)

}
