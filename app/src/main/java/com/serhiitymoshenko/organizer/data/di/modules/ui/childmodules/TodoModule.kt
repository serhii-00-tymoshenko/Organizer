package com.serhiitymoshenko.organizer.data.di.modules.ui.childmodules

import com.serhiitymoshenko.organizer.ui.home.todo.childfragments.addtask.viewmodel.AddTaskViewModel
import com.serhiitymoshenko.organizer.ui.home.todo.childfragments.edittask.viewmodel.EditTaskViewModel
import com.serhiitymoshenko.organizer.ui.home.todo.repositories.TasksRepository
import com.serhiitymoshenko.organizer.ui.home.todo.childfragments.tasks.deletedtasks.viewmodel.DeletedTasksViewModel
import com.serhiitymoshenko.organizer.ui.home.todo.childfragments.tasks.donetasks.viewmodel.DoneTasksViewModel
import com.serhiitymoshenko.organizer.ui.home.todo.childfragments.tasks.inprogresstasks.viewmodel.InProgressTasksViewModel
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