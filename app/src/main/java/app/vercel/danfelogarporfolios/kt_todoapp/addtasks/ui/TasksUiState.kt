package app.vercel.danfelogarporfolios.kt_todoapp.addtasks.ui

import app.vercel.danfelogarporfolios.kt_todoapp.addtasks.ui.model.TaskModel

interface TasksUiState {
    object  Loading:TasksUiState
    data class Error(val throwable: Throwable): TasksUiState
    data class Success(val tasks: List<TaskModel>): TasksUiState
}