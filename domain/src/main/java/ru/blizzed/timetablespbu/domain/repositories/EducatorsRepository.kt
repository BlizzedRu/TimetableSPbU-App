package ru.blizzed.timetablespbu.domain.repositories

import io.reactivex.Single
import ru.blizzed.timetablespbu.domain.entities.EducatorEntity

interface EducatorsRepository {

    fun search(query: String): Single<List<EducatorEntity>>

}