package ua.opu.kanbanapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ua.opu.kanbanapp.ui.screen.HabitScreen
import ua.opu.kanbanapp.ui.theme.KanbanAppTheme
import ua.opu.kanbanapp.ui.viewmodel.HabitViewModel

class MainActivity : ComponentActivity() {
    private val habitViewModel: HabitViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KanbanAppTheme {
                val habits by habitViewModel.habits.collectAsStateWithLifecycle()
                val completedCount by habitViewModel.completedCount.collectAsStateWithLifecycle()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HabitScreen(
                        habits = habits,
                        completedCount = completedCount,
                        onToggleHabit = habitViewModel::toggleHabit,
                        onResetDay = habitViewModel::resetDay,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
