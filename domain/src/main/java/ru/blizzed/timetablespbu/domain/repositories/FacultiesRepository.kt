package ru.blizzed.timetablespbu.domain.repositories

import io.reactivex.Single
import ru.blizzed.timetablespbu.domain.entities.Faculty

interface FacultiesRepository {

    fun getAll(): Single<List<Faculty>>

    fun search(query: String): Single<List<Faculty>>

}
