package com.serhiitymoshenko.organizer.data.di.modules.ui.children

import com.serhiitymoshenko.organizer.ui.home.todo.children.addtask.viewmodel.AddTaskViewModel
import com.serhiitymoshenko.organizer.ui.home.todo.children.edittask.viewmodel.EditTaskViewModel
import com.serhiitymoshenko.organizer.ui.home.todo.repositories.TasksRepository
import com.serhiitymoshenko.organizer.ui.home.todo.children.tasks.deletedtasks.viewmodel.DeletedTasksViewModel
import com.serhiitymoshenko.organizer.ui.home.todo.children.tasks.donetasks.viewmodel.DoneTasksViewModel
import com.serhiitymoshenko.organizer.ui.home.todo.children.tasks.inprogresstasks.viewmodel.InProgressTasksViewModel
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