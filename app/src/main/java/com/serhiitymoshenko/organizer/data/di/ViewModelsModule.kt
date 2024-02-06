package com.serhiitymoshenko.organizer.data.di

import com.serhiitymoshenko.contacts.ui.home.viewmodel.ContactsViewModel
import com.serhiitymoshenko.contacts.ui.repositories.ContactsRepository
import com.serhiitymoshenko.organizer.ui.home.todo.edittask.viewmodel.EditTaskViewModel
import com.serhiitymoshenko.organizer.ui.home.todo.tasks.inprogresstasks.viewmodel.InProgressTasksViewModel
import com.serhiitymoshenko.organizer.ui.home.todo.repositories.TasksRepository
import com.serhiitymoshenko.organizer.ui.home.todo.viewmodel.TodoViewModel
import org.koin.dsl.module

val viewModelsModule = module {

    single<TasksRepository> {
        TasksRepository(get())
    }

    single<ContactsRepository> {
        ContactsRepository(get())
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

    single<ContactsViewModel> {
        ContactsViewModel(get())
    }
}