package ua.opu.kanbanapp.data.model

data class Habit(
    val id: Int,
    val title: String,
    val isDone: Boolean = false
)
