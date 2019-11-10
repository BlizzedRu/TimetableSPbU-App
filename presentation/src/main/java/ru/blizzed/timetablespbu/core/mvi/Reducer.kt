package ru.blizzed.timetablespbu.core.mvi

typealias Reducer<State, Change> = (oldState: State, change: Change) -> State
