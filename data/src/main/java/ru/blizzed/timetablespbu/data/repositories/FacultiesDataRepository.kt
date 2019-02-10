package ru.blizzed.timetablespbu.data.repositories

import io.reactivex.Single
import ru.blizzed.timetablespbu.data.filesystem.FacultiesProvider
import ru.blizzed.timetablespbu.domain.entities.Faculty
import ru.blizzed.timetablespbu.domain.repositories.FacultiesRepository

class FacultiesDataRepository(private val facultiesProvider: FacultiesProvider) : FacultiesRepository {

    override fun getFaculties(): Single<List<Faculty>> = Single.fromCallable(facultiesProvider::getFaculties)

}