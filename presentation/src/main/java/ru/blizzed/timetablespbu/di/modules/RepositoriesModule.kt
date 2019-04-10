package ru.blizzed.timetablespbu.di.modules

import dagger.Module
import dagger.Provides
import ru.blizzed.timetablespbu.data.datasources.EducatorsRemoteDataSource
import ru.blizzed.timetablespbu.data.datasources.FacultiesAssetsDataSource
import ru.blizzed.timetablespbu.data.repositories.EducatorsDataRepository
import ru.blizzed.timetablespbu.data.repositories.FacultiesDataRepository
import ru.blizzed.timetablespbu.data.utils.AssetsUtil
import ru.blizzed.timetablespbu.di.scopes.App
import ru.blizzed.timetablespbu.domain.datasources.EducatorsDataSource
import ru.blizzed.timetablespbu.domain.datasources.FacultiesDataSource
import ru.blizzed.timetablespbu.domain.mappers.EducatorMapper
import ru.blizzed.timetablespbu.domain.repositories.EducatorsRepository
import ru.blizzed.timetablespbu.domain.repositories.FacultiesRepository
import ru.blizzed.timetablespbulib.methods.EducatorsApiMethod

@Module
class RepositoriesModule {

    @App
    @Provides
    fun provideEducatorsRemoteDataSource(
            educatorsApiMethod: EducatorsApiMethod,
            educatorsMapper: EducatorMapper
    ): EducatorsDataSource = EducatorsRemoteDataSource(educatorsApiMethod, educatorsMapper)

    @App
    @Provides
    fun provideEducatorsRepository(educatorsDataSource: EducatorsDataSource): EducatorsRepository = EducatorsDataRepository(educatorsDataSource)

    @App
    @Provides
    fun provideFacultiesDataSource(assetsUtil: AssetsUtil): FacultiesDataSource = FacultiesAssetsDataSource(assetsUtil)

    @App
    @Provides
    fun provideFacultiesRepository(facultiesDataSource: FacultiesDataSource): FacultiesRepository = FacultiesDataRepository(facultiesDataSource)

}