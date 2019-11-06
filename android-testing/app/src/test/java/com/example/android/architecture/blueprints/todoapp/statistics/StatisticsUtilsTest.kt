package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
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
        assertThat(result.completedTasksPercent, `is`(0f))
        assertThat(result.activeTasksPercent, `is`(100f))
    }

    // If there are 2 completed tasks and 3 active tasks,
    // then there are 40% completed task and 60% active task.
    @Test
    fun getActiveAndCompletedStats_noCompleted_returnsFortySixty() {
        // GIVEN
        val tasks = listOf(
                Task("Title", "Description", isCompleted = true),
                Task("Title", "Description", isCompleted = true),
                Task("Title", "Description", isCompleted = false),
                Task("Title", "Description", isCompleted = false),
                Task("Title", "Description", isCompleted = false)
        )

        // WHEN
        val result = getActiveAndCompletedStats(tasks)

        // THEN
        assertThat(result.completedTasksPercent, `is`(40f))
        assertThat(result.activeTasksPercent, `is`(60f))
    }

    @Test
    fun getActiveAndCompletedStats_empty_returnZero() {
        val tasks = listOf<Task>()
        val result = getActiveAndCompletedStats(tasks)
        assertThat(result.completedTasksPercent, `is`(0f))
        assertThat(result.activeTasksPercent, `is`(0f))
    }

    @Test
    fun getActiveAndCompletedStats_null_returnZero() {
        val result = getActiveAndCompletedStats(null)
        assertThat(result.completedTasksPercent, `is`(0f))
        assertThat(result.activeTasksPercent, `is`(0f))
    }
}