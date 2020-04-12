package ru.blizzed.timetablespbu.data.repositories

import io.reactivex.Single
import ru.blizzed.timetablespbu.data.datasources.DivisionsCacheDataSource
import ru.blizzed.timetablespbu.data.datasources.DivisionsRemoteDataSource
import ru.blizzed.timetablespbu.data.datasources.GroupsCacheDataSource
import ru.blizzed.timetablespbu.domain.entities.AdmissionYearGroups
import ru.blizzed.timetablespbu.domain.entities.FacultyAlias
import ru.blizzed.timetablespbu.domain.entities.Group
import ru.blizzed.timetablespbu.domain.entities.StudyLevel
import ru.blizzed.timetablespbu.domain.entities.StudyProgramCombination
import ru.blizzed.timetablespbu.domain.entities.StudyProgramId
import ru.blizzed.timetablespbu.domain.repositories.TimetableBrowseRepository
import java.util.concurrent.TimeUnit

class TimetableBrowseDataRepository(
  private val divisionsRemoteDataSource: DivisionsRemoteDataSource,
  private val divisionsCacheDataSource: DivisionsCacheDataSource,
  private val groupsCacheDataSource: GroupsCacheDataSource
) : TimetableBrowseRepository {

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

  override fun getGroupsByProgramId(programId: StudyProgramId): Single<List<Group>> =
    groupsCacheDataSource.getOrNull(programId)
      ?.let { Single.just(it) }
      ?: divisionsRemoteDataSource.getProgramGroups(programId)
        .doOnSuccess { groups -> groupsCacheDataSource.put(programId, groups) }

  override fun getAdmissionYearGroups(
    facultyAlias: FacultyAlias,
    studyLevelId: Int,
    programId: StudyProgramId
  ): Single<List<AdmissionYearGroups>> = getProgramCombinations(facultyAlias, studyLevelId)
    .map { programs -> programs.find { it.id == programId } }
    .flatMap { program ->
      val requests = program.admissionYears
        .mapIndexed { index, admissionYear ->
          getGroupsByProgramId(admissionYear.studyProgramId)
            .run {
              if (groupsCacheDataSource.getOrNull(programId) == null) {
                // Delay to not spam server
                delay(REQUEST_DELAY_MS * index, TimeUnit.MILLISECONDS)
              } else {
                this
              }
            }
            .map { groups -> AdmissionYearGroups(admissionYear, groups) }
        }

      Single.zip(requests) {
        @Suppress("UNCHECKED_CAST")
        it.toList() as List<AdmissionYearGroups>
      }
    }

  companion object {
    private const val REQUEST_DELAY_MS = 50L
  }
}
