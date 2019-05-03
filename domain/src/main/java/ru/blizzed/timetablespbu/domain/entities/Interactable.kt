package ru.blizzed.timetablespbu.domain.entities

open class Interactable(
        isFavorite: Boolean = false,
        isViewed: Boolean = false,
        lastInteractionTime: Long = 0L
) {

    var lastInteractionTime: Long = lastInteractionTime
        private set

    var isViewed: Boolean = isViewed
        private set

    var isFavorite: Boolean = isFavorite
        private set

    fun view(time: Long) {
        isViewed = true
        lastInteractionTime = time
    }

    fun setFavorite(isFavorite: Boolean, time: Long = 0L) {
        this.isFavorite = isFavorite
        if (isFavorite) lastInteractionTime = time
    }

}
