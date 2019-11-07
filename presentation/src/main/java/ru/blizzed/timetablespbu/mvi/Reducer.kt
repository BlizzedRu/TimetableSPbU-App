package ru.blizzed.timetablespbu.mvi

typealias Reducer<State, Change> = (oldState: State, change: Change) -> State
