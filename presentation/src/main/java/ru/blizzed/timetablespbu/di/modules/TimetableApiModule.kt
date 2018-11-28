package ru.blizzed.timetablespbu.di.modules

import dagger.Module
import dagger.Provides
import ru.blizzed.timetablespbu.di.scopes.App
import ru.blizzed.timetablespbulib.TimeTableApi
import ru.blizzed.timetablespbulib.methods.*

@Module
class TimetableApiModule {

    @App
    @Provides
    fun provideAddressesApiMethod(): AddressesApiMethod {
        return TimeTableApi.addresses()
    }

    @App
    @Provides
    fun provideEducatorsApiMethod(): EducatorsApiMethod {
        return TimeTableApi.educators()
    }

    @App
    @Provides
    fun provideExtracurDivisionsApiMethod(): ExtracurDivisionsApiMethod {
        return TimeTableApi.extracurDivisions()
    }

    @App
    @Provides
    fun provideGroupsApiMethod(): GroupsApiMethod {
        return TimeTableApi.groups()
    }

    @App
    @Provides
    fun provideDivisionsApiMethod(): DivisionsApiMethod {
        return TimeTableApi.divisions()
    }

}