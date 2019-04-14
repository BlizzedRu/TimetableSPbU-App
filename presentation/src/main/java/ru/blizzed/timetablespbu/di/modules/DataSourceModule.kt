package ru.blizzed.timetablespbu.di.modules

import dagger.Module
import dagger.Provides
import ru.blizzed.timetablespbu.data.datasources.EducatorsLocalDataSource
import ru.blizzed.timetablespbu.data.datasources.EducatorsRemoteDataSource
import ru.blizzed.timetablespbu.data.datasources.FacultiesAssetsDataSource
import ru.blizzed.timetablespbu.data.repositories.EducatorsDataRepository
import ru.blizzed.timetablespbu.data.repositories.FacultiesDataRepository
import ru.blizzed.timetablespbu.data.utils.TimeUtils
import ru.blizzed.timetablespbu.di.scopes.App
import ru.blizzed.timetablespbu.domain.repositories.EducatorsRepository
import ru.blizzed.timetablespbu.domain.repositories.FacultiesRepository

@Module
class RepositoriesModule {

    @App
    @Provides
    fun provideEducatorsRepository(
            localDataSource: EducatorsLocalDataSource,
            remoteDataSource: EducatorsRemoteDataSource,
            timeUtils: TimeUtils
    ): EducatorsRepository = EducatorsDataRepository(localDataSource, remoteDataSource, timeUtils)

    @App
    @Provides
    fun provideFacultiesRepository(assetsDataSource: FacultiesAssetsDataSource): FacultiesRepository = FacultiesDataRepository(assetsDataSource)

}
