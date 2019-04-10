package ru.blizzed.timetablespbu.data.repositories

import io.reactivex.Single
import ru.blizzed.timetablespbu.domain.datasources.FacultiesDataSource
import ru.blizzed.timetablespbu.domain.entities.Faculty
import ru.blizzed.timetablespbu.domain.repositories.FacultiesRepository

class FacultiesDataRepository(facultiesDataSource: FacultiesDataSource) : FacultiesRepository {

    private val facultiesSingle = Single.fromCallable(facultiesDataSource::getFaculties).cache()

    override fun getFaculties(): Single<List<Faculty>> = facultiesSingle

}