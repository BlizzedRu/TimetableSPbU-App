package ru.blizzed.timetablespbu.domain.datasources

import io.reactivex.Single
import ru.blizzed.timetablespbu.domain.entities.Educator

interface EducatorsDataSource {
    fun search(query: String): Single<List<Educator>>
}