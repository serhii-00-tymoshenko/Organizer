package com.serhiitymoshenko.organizer.data.di.modules.ui.childmodules

import com.serhiitymoshenko.organizer.ui.home.todo.addtask.viewmodel.AddTaskViewModel
import com.serhiitymoshenko.organizer.ui.home.todo.edittask.viewmodel.EditTaskViewModel
import com.serhiitymoshenko.organizer.ui.home.todo.repositories.TasksRepository
import com.serhiitymoshenko.organizer.ui.home.todo.tasks.deletedtasks.viewmodel.DeletedTasksViewModel
import com.serhiitymoshenko.organizer.ui.home.todo.tasks.donetasks.viewmodel.DoneTasksViewModel
import com.serhiitymoshenko.organizer.ui.home.todo.tasks.inprogresstasks.viewmodel.InProgressTasksViewModel
import com.serhiitymoshenko.organizer.ui.home.todo.viewmodel.TodoHomeViewModel
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