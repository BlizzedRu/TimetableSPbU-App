package ru.blizzed.timetablespbu.data.datasources

import ru.blizzed.timetablespbu.data.utils.AssetsUtil
import ru.blizzed.timetablespbu.domain.datasources.FacultiesDataSource
import ru.blizzed.timetablespbu.domain.entities.Faculty

class FacultiesAssetsDataSource(private val assetsUtil: AssetsUtil) : FacultiesDataSource {

    companion object {
        private const val FACULTIES_FOLDER_NAME = "faculties"
        private const val FACULTIES_INFO_FILE_NAME = "info.json"
    }

    override fun getFaculties(): List<Faculty> = assetsUtil
            .getListModel(FACULTIES_FOLDER_NAME, FACULTIES_INFO_FILE_NAME, Faculty::class.java)
            .also { faculties ->
                faculties.forEach { faculty -> faculty.logo = faculty.logo.prependIndent("$FACULTIES_FOLDER_NAME/") }
            }

}