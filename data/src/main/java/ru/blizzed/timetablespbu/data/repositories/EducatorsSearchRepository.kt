package ru.blizzed.timetablespbu.data.repositories

import io.reactivex.Single
import ru.blizzed.timetablespbu.data.extensions.executeAsync
import ru.blizzed.timetablespbu.domain.entities.EducatorEntity
import ru.blizzed.timetablespbu.domain.mappers.EducatorMapper
import ru.blizzed.timetablespbulib.methods.EducatorsApiMethod
import ru.blizzed.timetablespbulib.model.educators.EducatorSearchResult
import javax.inject.Inject

class EducatorsSearchRepository @Inject constructor(
    private val educatorsApi: EducatorsApiMethod,
    private val educatorMapper: EducatorMapper
) : EducatorsRepository {

    override fun search(query: String): Single<List<EducatorEntity>> = educatorsApi
        .search(query)
        .executeAsync()
        .map(EducatorSearchResult::getEducators)
        .map(educatorMapper::apply)

}