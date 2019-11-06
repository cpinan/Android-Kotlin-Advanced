package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import org.junit.Assert.assertEquals
import org.junit.Test

class StatisticsUtilsTest {

    // If there are no completed task and one active task,
    // then there are 100% active task and 0% completed task.
    @Test
    fun getActiveAndCompletedStats_noCompleted_returnsHundredZero() {
        val tasks = listOf(
                Task(
                        "Title",
                        "Description",
                        isCompleted = false
                )
        )
        val result = getActiveAndCompletedStats(tasks)
        assertEquals(0.0f, result.completedTasksPercent)
        assertEquals(100.0f, result.activeTasksPercent)
    }

    // If there are 2 completed tasks and 3 active tasks,
    // then there are 40% completed task and 60% active task.
    @Test
    fun getActiveAndCompletedStats_noCompleted_returnsFortySixty() {
        val tasks = listOf(
                Task("Title", "Description", isCompleted = true),
                Task("Title", "Description", isCompleted = true),
                Task("Title", "Description", isCompleted = false),
                Task("Title", "Description", isCompleted = false),
                Task("Title", "Description", isCompleted = false)
        )
        val result = getActiveAndCompletedStats(tasks)
        assertEquals(40.0f, result.completedTasksPercent)
        assertEquals(60.0f, result.activeTasksPercent)
    }
}