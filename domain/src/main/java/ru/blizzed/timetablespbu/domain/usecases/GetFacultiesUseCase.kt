package ru.blizzed.timetablespbu.domain.usecases

import ru.blizzed.timetablespbu.domain.repositories.FacultiesRepository

class GetFacultiesUseCase(private val facultiesRepository: FacultiesRepository) {

  operator fun invoke() = facultiesRepository.getAll()

}
