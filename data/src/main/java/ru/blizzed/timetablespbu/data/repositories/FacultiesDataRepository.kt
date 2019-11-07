package ru.blizzed.timetablespbu.data.repositories

import io.reactivex.Single
import io.reactivex.subjects.SingleSubject
import ru.blizzed.timetablespbu.data.datasources.FacultiesAssetsDataSource
import ru.blizzed.timetablespbu.domain.entities.Faculty
import ru.blizzed.timetablespbu.domain.repositories.FacultiesRepository

class FacultiesDataRepository(
        private val facultiesDataSource: FacultiesAssetsDataSource
) : FacultiesRepository {

    private val cache = SingleSubject.create<List<Faculty>>()

    override fun getAll(): Single<List<Faculty>> = if (cache.hasValue()) cache else fetch()

    override fun search(query: String): Single<List<Faculty>> = getAll()
            .map { faculties ->
                faculties.filter { faculty ->
                    faculty.name.contains(query, ignoreCase = true) || faculty.alias.contains(query, ignoreCase = true)
                }
            }

    private fun fetch() = facultiesDataSource.getFaculties().doOnSuccess(cache::onSuccess)

}
