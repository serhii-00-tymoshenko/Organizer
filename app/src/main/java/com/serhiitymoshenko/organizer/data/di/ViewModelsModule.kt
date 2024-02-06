package com.serhiitymoshenko.organizer.data.di

import com.serhiitymoshenko.organizer.ui.organizer.todo.edittask.viewmodel.EditTaskViewModel
import com.serhiitymoshenko.organizer.ui.organizer.todo.tasks.inprogresstasks.viewmodel.InProgressTasksViewModel
import com.serhiitymoshenko.organizer.ui.organizer.todo.repositories.TasksRepository
import com.serhiitymoshenko.organizer.ui.organizer.todo.viewmodel.TodoViewModel
import org.koin.dsl.module

val viewModelsModule = module {
    single<TasksRepository> {
        TasksRepository(get())
    }

    single<InProgressTasksViewModel> {
        InProgressTasksViewModel(get())
    }

    single<TodoViewModel> {
        TodoViewModel(get())
    }

    single<EditTaskViewModel> {
        EditTaskViewModel(get())
    }
}