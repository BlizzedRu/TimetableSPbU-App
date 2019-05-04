package ru.blizzed.timetablespbu.data.datasources

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import ru.blizzed.timetablespbu.data.IOScheduler
import ru.blizzed.timetablespbu.data.utils.AssetsUtil
import ru.blizzed.timetablespbu.domain.entities.Faculty
import javax.inject.Inject

class FacultiesAssetsDataSource @Inject constructor(
        private val assetsUtil: AssetsUtil,
        @IOScheduler private val ioScheduler: Scheduler
) {

    companion object {
        private const val FACULTIES_FOLDER_NAME = "faculties"
        private const val FACULTIES_INFO_FILE_NAME = "info.json"
    }

    private var facultiesSubject = BehaviorSubject.create<List<Faculty>>()

    fun getFaculties(): Single<List<Faculty>> {
        return if (facultiesSubject.hasValue()) {
            facultiesSubject.singleOrError()
        } else {
            fetch()
        }
    }

    private fun fetch(): Single<List<Faculty>> = Single
            .fromCallable {
                assetsUtil
                        .getListModel(FACULTIES_FOLDER_NAME, FACULTIES_INFO_FILE_NAME, Faculty::class.java)
                        .also { faculties ->
                            faculties.forEach { faculty -> faculty.logo = faculty.logo.prependIndent("$FACULTIES_FOLDER_NAME/") }
                            facultiesSubject.onNext(faculties)
                        }
            }.subscribeOn(ioScheduler)

}
