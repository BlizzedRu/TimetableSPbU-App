package ru.blizzed.timetablespbu.di.modules

import dagger.Module
import dagger.Provides
import ru.blizzed.timetablespbu.data.filesystem.FacultiesProvider
import ru.blizzed.timetablespbu.data.repositories.EducatorsDataRepository
import ru.blizzed.timetablespbu.data.repositories.FacultiesDataRepository
import ru.blizzed.timetablespbu.di.scopes.App
import ru.blizzed.timetablespbu.domain.mappers.EducatorMapper
import ru.blizzed.timetablespbu.domain.repositories.EducatorsRepository
import ru.blizzed.timetablespbu.domain.repositories.FacultiesRepository
import ru.blizzed.timetablespbulib.methods.AddressesApiMethod
import ru.blizzed.timetablespbulib.methods.EducatorsApiMethod

@Module
class RepositoriesModule {

    @App
    @Provides
    fun provideEducatorsRepository(
        educatorsApi: EducatorsApiMethod,
        educatorMapper: EducatorMapper,
        addressesApi: AddressesApiMethod
    ): EducatorsRepository = EducatorsDataRepository(educatorsApi, educatorMapper, addressesApi)

    @App
    @Provides
    fun provideFacultiesRepository(facultiesProvider: FacultiesProvider): FacultiesRepository = FacultiesDataRepository(facultiesProvider)

}