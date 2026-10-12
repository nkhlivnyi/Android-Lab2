package ua.opu.kanbanapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import ua.opu.kanbanapp.data.model.Habit

class HabitViewModel : ViewModel() {
    private val initialHabits = listOf(
        Habit(1, "Випити склянку води"),
        Habit(2, "Зробити зарядку"),
        Habit(3, "Поснідати"),
        Habit(4, "Прочитати 10 сторінок"),
        Habit(5, "Прогулятися"),
        Habit(6, "Лягти спати вчасно")
    )

    private val _habits = MutableStateFlow(initialHabits)
    val habits: StateFlow<List<Habit>> = _habits.asStateFlow()

    val completedCount: StateFlow<Int> = habits
        .map { list -> list.count { habit -> habit.isDone } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 0
        )

    fun toggleHabit(id: Int) {
        _habits.update { list ->
            list.map { habit ->
                if (habit.id == id) habit.copy(isDone = !habit.isDone) else habit
            }
        }
    }

    fun resetDay() {
        _habits.update { list -> list.map { habit -> habit.copy(isDone = false) } }
    }
}
