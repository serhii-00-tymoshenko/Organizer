package com.serhiitymoshenko.organizer.data.di

import com.serhiitymoshenko.organizer.ui.organizer.todo.tasks.inprogresstasks.viewmodel.InProgressTasksViewModel
import com.serhiitymoshenko.organizer.ui.organizer.todo.tasks.repositories.TasksRepository
import org.koin.dsl.module

val viewModelsModule = module {
    single<TasksRepository> {
        TasksRepository(get())
    }

    single<InProgressTasksViewModel> {
        InProgressTasksViewModel(get())
    }
}