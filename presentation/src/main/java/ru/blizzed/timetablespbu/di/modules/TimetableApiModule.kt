package ru.blizzed.timetablespbu.di.modules

import dagger.Module
import dagger.Provides
import ru.blizzed.timetablespbu.di.scopes.Screens
import ru.blizzed.timetablespbulib.TimeTableApi
import ru.blizzed.timetablespbulib.methods.*

@Module
class TimetableApiModule {

    @Screens
    @Provides
    fun provideAddressesApiMethod(): AddressesApiMethod {
        return TimeTableApi.addresses()
    }

    @Screens
    @Provides
    fun provideEducatorsApiMethod(): EducatorsApiMethod {
        return TimeTableApi.educators()
    }

    @Screens
    @Provides
    fun provideExtracurDivisionsApiMethod(): ExtracurDivisionsApiMethod {
        return TimeTableApi.extracurDivisions()
    }

    @Screens
    @Provides
    fun provideGroupsApiMethod(): GroupsApiMethod {
        return TimeTableApi.groups()
    }

    @Screens
    @Provides
    fun provideDivisionsApiMethod(): DivisionsApiMethod {
        return TimeTableApi.divisions()
    }

}