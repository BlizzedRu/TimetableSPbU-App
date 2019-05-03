package ru.blizzed.timetablespbu.di.modules

import dagger.Module
import dagger.Provides
import ru.blizzed.timetablespbu.di.scopes.App
import ru.blizzed.timetablespbulib.TimeTableApi
import ru.blizzed.timetablespbulib.methods.AddressesApiMethod
import ru.blizzed.timetablespbulib.methods.DivisionsApiMethod
import ru.blizzed.timetablespbulib.methods.EducatorsApiMethod
import ru.blizzed.timetablespbulib.methods.ExtracurDivisionsApiMethod
import ru.blizzed.timetablespbulib.methods.GroupsApiMethod

@Module
class TimetableApiModule {

    @App
    @Provides
    fun provideAddressesApiMethod(): AddressesApiMethod = TimeTableApi.addresses()

    @App
    @Provides
    fun provideEducatorsApiMethod(): EducatorsApiMethod = TimeTableApi.educators()

    @App
    @Provides
    fun provideExtracurDivisionsApiMethod(): ExtracurDivisionsApiMethod = TimeTableApi.extracurDivisions()

    @App
    @Provides
    fun provideGroupsApiMethod(): GroupsApiMethod = TimeTableApi.groups()

    @App
    @Provides
    fun provideDivisionsApiMethod(): DivisionsApiMethod = TimeTableApi.divisions()

}
