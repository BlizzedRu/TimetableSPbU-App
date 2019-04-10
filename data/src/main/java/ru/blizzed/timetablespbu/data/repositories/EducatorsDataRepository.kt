package ru.blizzed.timetablespbu.data.repositories

import io.reactivex.Single
import ru.blizzed.timetablespbu.domain.datasources.EducatorsDataSource
import ru.blizzed.timetablespbu.domain.entities.Educator
import ru.blizzed.timetablespbu.domain.repositories.EducatorsRepository

class EducatorsDataRepository(
        private val educatorsRemoteDataSource: EducatorsDataSource
) : EducatorsRepository {

    override fun search(query: String): Single<List<Educator>> = educatorsRemoteDataSource.search(query)

}