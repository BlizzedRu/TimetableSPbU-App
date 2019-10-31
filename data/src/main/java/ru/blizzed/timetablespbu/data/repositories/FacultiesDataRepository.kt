package ru.blizzed.timetablespbu.data.repositories

import io.reactivex.Single
import ru.blizzed.timetablespbu.data.datasources.FacultiesAssetsDataSource
import ru.blizzed.timetablespbu.domain.entities.Faculty
import ru.blizzed.timetablespbu.domain.repositories.FacultiesRepository

class FacultiesDataRepository(
    private val facultiesDataSource: FacultiesAssetsDataSource
) : FacultiesRepository {

    override fun getFaculties(): Single<List<Faculty>> = facultiesDataSource.getFaculties()

}
