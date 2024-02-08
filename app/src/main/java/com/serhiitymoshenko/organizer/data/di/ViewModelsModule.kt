package com.serhiitymoshenko.organizer.data.di

import com.serhiitymoshenko.organizer.ui.home.contacts.viewmodel.ContactsViewModel
import com.serhiitymoshenko.contacts.ui.repositories.ContactsRepository
import com.serhiitymoshenko.organizer.ui.home.contacts.addcontact.viewmodel.AddContactViewModel
import com.serhiitymoshenko.organizer.ui.home.todo.addtask.viewmodel.AddTaskViewModel
import com.serhiitymoshenko.organizer.ui.home.todo.edittask.viewmodel.EditTaskViewModel
import com.serhiitymoshenko.organizer.ui.home.todo.tasks.inprogresstasks.viewmodel.InProgressTasksViewModel
import com.serhiitymoshenko.organizer.ui.home.todo.repositories.TasksRepository
import com.serhiitymoshenko.organizer.ui.home.todo.viewmodel.TodoViewModel
import com.serhiitymoshenko.organizer.ui.home.viewmodel.HomeViewModel
import com.serhiitymoshenko.organizer.ui.repositories.DataStoreRepository
import org.koin.dsl.module

val viewModelsModule = module {

    single<TasksRepository> {
        TasksRepository(get())
    }

    single<ContactsRepository> {
        ContactsRepository(get())
    }

    single<DataStoreRepository> {
        DataStoreRepository(get())
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

    single<AddContactViewModel> {
        AddContactViewModel(get())
    }

    single<HomeViewModel> {
        HomeViewModel(get())
    }

    single<AddTaskViewModel> {
        AddTaskViewModel(get())
    }
}