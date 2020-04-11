package ru.blizzed.timetablespbu.data.datasources

import io.reactivex.Scheduler
import io.reactivex.Single
import ru.blizzed.timetablespbu.data.extensions.executeAsync
import ru.blizzed.timetablespbu.domain.entities.Group
import ru.blizzed.timetablespbu.domain.entities.StudyLevel
import ru.blizzed.timetablespbu.domain.mappers.GroupMapper.mapToEntity
import ru.blizzed.timetablespbu.domain.mappers.StudyLevelMapper.mapToEntity
import ru.blizzed.timetablespbulib.methods.DivisionsApiMethod

class DivisionsRemoteDataSource(
        private val divisionsApi: DivisionsApiMethod,
        ioScheduler: Scheduler
) : IODataSource(ioScheduler) {

    fun getStudyLevels(divisionAlias: String): Single<List<StudyLevel>> = divisionsApi
            .getProgramLevels(divisionAlias)
            .executeAsync()
            .map { it.mapToEntity() }
            .onIoScheduler()


    fun getProgramGroups(programId: Int): Single<List<Group>> = divisionsApi
            .getProgramGroups(programId)
            .executeAsync()
            .map { it.mapToEntity() }
            .onIoScheduler()

}
