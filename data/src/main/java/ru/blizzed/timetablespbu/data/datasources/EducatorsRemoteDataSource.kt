package ru.blizzed.timetablespbu.data.datasources

import io.reactivex.Single
import ru.blizzed.timetablespbu.data.extensions.executeAsync
import ru.blizzed.timetablespbu.domain.entities.Educator
import ru.blizzed.timetablespbu.domain.mappers.EducatorMapper.mapToEntity
import ru.blizzed.timetablespbulib.methods.EducatorsApiMethod
import ru.blizzed.timetablespbulib.model.educators.EducatorSearchResult

class EducatorsRemoteDataSource(
        private val educatorsApi: EducatorsApiMethod
) {

    fun search(query: String): Single<List<Educator>> = educatorsApi
            .search(query)
            .executeAsync()
            .map(EducatorSearchResult::getEducators)
            .map { it.mapToEntity() }

}
