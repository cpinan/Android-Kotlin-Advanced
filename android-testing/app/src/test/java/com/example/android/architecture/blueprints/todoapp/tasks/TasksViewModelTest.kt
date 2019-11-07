package com.example.android.architecture.blueprints.todoapp.tasks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.android.architecture.blueprints.todoapp.Event
import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.data.source.FakeTestRepository
import com.example.android.architecture.blueprints.todoapp.getOrAwaitValue
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TasksViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    // Subject under test
    private lateinit var tasksViewModel: TasksViewModel

    private lateinit var tasksRepository: FakeTestRepository

    @Before
    fun setupViewModel() {
        // We initialise the tasks to 3, with one active and two completed
        tasksRepository = FakeTestRepository()
        val task1 = Task("Title1", "Description1")
        val task2 = Task("Title2", "Description2", true)
        val task3 = Task("Title3", "Description3", true)
        tasksRepository.addTasks(task1, task2, task3)

        tasksViewModel = TasksViewModel(tasksRepository)
    }

    @Test
    fun addNewTask_setsNewTaskEvent() {

        // Given a fresh TasksViewModel
/*
        val tasksViewModel = TasksViewModel(
                ApplicationProvider.getApplicationContext()
        )
 */

        val observer = Observer<Event<Unit>> {}
        try {
            tasksViewModel.newTaskEvent.observeForever(observer)

            // When adding a new task
            tasksViewModel.addNewTask()

            // Then the new task event is triggered
            val value = tasksViewModel.newTaskEvent.value
            assertThat(value?.getContentIfNotHandled(), (not(nullValue())))
        } finally {
            tasksViewModel.newTaskEvent.removeObserver(observer)
        }

    }

    @Test
    fun addNewTask_setsNewTaskEventWithUtils() {

        // Given a fresh TasksViewModel
/*
        val tasksViewModel = TasksViewModel(
                ApplicationProvider.getApplicationContext()
        )

 */
        // When adding a new task
        tasksViewModel.addNewTask()

        // Then the new task event is triggered
        val value = tasksViewModel.newTaskEvent.getOrAwaitValue()
        assertThat(
                value.getContentIfNotHandled(),
                (not(nullValue()))
        )

    }

    @Test
    fun setFilterAllTasks_tasksAddViewVisible() {
        // Given a fresh ViewModel
        /*
        val tasksViewModel = TasksViewModel(
                ApplicationProvider.getApplicationContext()
        )
    */
        // When the filter type is ALL_TASKS
        tasksViewModel.setFiltering(TasksFilterType.ALL_TASKS)

        // Then the "Add task" action is visible
        val value = tasksViewModel.tasksAddViewVisible.getOrAwaitValue()
        assertThat(
                value,
                `is`(true)
        )

    }
}