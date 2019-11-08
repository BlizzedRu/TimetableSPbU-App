package ru.blizzed.timetablespbu.data.datasources

import io.reactivex.Scheduler
import io.reactivex.Single
import ru.blizzed.timetablespbu.data.utils.AssetsUtil
import ru.blizzed.timetablespbu.domain.entities.Faculty

class FacultiesAssetsDataSource(
        private val assetsUtil: AssetsUtil,
        private val ioScheduler: Scheduler
) {

    companion object {
        private const val FACULTIES_FOLDER_NAME = "faculties"
        private const val FACULTIES_INFO_FILE_NAME = "info.json"
    }

    fun getFaculties(): Single<List<Faculty>> = Single
            .fromCallable {
                assetsUtil
                        .getListModel(FACULTIES_FOLDER_NAME, FACULTIES_INFO_FILE_NAME, Faculty::class.java)
                        .also { faculties ->
                            faculties.forEach { faculty ->
                                faculty.logo = faculty.logo.prependIndent("$FACULTIES_FOLDER_NAME/")
                            }
                        }
            }
            .subscribeOn(ioScheduler)

}
