package com.serhiitymoshenko.organizer.data.di.modules.ui

import com.serhiitymoshenko.organizer.ui.home.todo.addtask.AddTaskViewModel
import com.serhiitymoshenko.organizer.ui.home.todo.edittask.EditTaskViewModel
import com.serhiitymoshenko.organizer.ui.home.todo.repositories.TasksRepository
import com.serhiitymoshenko.organizer.ui.home.todo.tasks.deletedtasks.DeletedTasksViewModel
import com.serhiitymoshenko.organizer.ui.home.todo.tasks.donetasks.DoneTasksViewModel
import com.serhiitymoshenko.organizer.ui.home.todo.tasks.inprogresstasks.InProgressTasksViewModel
import com.serhiitymoshenko.organizer.ui.home.todo.home.TodoHomeViewModel
import org.koin.dsl.module

val todoModule = module {

    single<TasksRepository> {
        TasksRepository(get())
    }

    single<AddTaskViewModel> {
        AddTaskViewModel(get())
    }

    single<InProgressTasksViewModel> {
        InProgressTasksViewModel(get())
    }

    single<DoneTasksViewModel> {
        DoneTasksViewModel(get())
    }

    single<DeletedTasksViewModel> {
        DeletedTasksViewModel(get())
    }

    single<TodoHomeViewModel> {
        TodoHomeViewModel(get())
    }

    single<EditTaskViewModel> {
        EditTaskViewModel(get())
    }
}