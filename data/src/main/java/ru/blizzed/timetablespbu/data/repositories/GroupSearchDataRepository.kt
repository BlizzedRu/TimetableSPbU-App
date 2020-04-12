package ru.blizzed.timetablespbu.data.repositories

import io.reactivex.Single
import ru.blizzed.timetablespbu.data.datasources.DivisionsCacheDataSource
import ru.blizzed.timetablespbu.data.datasources.DivisionsRemoteDataSource
import ru.blizzed.timetablespbu.domain.entities.Group
import ru.blizzed.timetablespbu.domain.entities.StudyLevel
import ru.blizzed.timetablespbu.domain.entities.StudyProgramCombination
import ru.blizzed.timetablespbu.domain.repositories.GroupSearchRepository

class GroupSearchDataRepository(
  private val divisionsRemoteDataSource: DivisionsRemoteDataSource,
  private val divisionsCacheDataSource: DivisionsCacheDataSource
) : GroupSearchRepository {

  override fun getStudyLevelsByDivisionAlias(facultyAlias: String): Single<List<StudyLevel>> =
    divisionsCacheDataSource.getOrNull(facultyAlias)
      ?.let { Single.just(it) }
      ?: divisionsRemoteDataSource.getStudyLevels(facultyAlias)
        .doOnSuccess { levels -> divisionsCacheDataSource.put(facultyAlias, levels) }

  override fun getProgramCombinations(facultyAlias: String, studyLevelId: Int): Single<List<StudyProgramCombination>> =
    Single.fromCallable {
      divisionsCacheDataSource.get(facultyAlias)
        .find { it.id == studyLevelId }
        ?.studyProgramCombinations
        ?: throw IllegalStateException("Study combinations not found for faculty $facultyAlias and study level id $studyLevelId")
    }

  override fun getGroupsByProgramId(programId: Int): Single<List<Group>> =
    divisionsRemoteDataSource.getProgramGroups(programId)

}
