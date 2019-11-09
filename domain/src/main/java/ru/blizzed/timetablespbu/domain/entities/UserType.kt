package ru.blizzed.timetablespbu.domain.entities

sealed class UserType {
    class Student(val studentInfo: StudentInfo) : UserType()
    class Educator(val educatorInfo: EducatorInfo) : UserType()
}
