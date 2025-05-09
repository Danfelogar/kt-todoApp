package app.vercel.danfelogarporfolios.kt_todoapp.addtasks.domain

import app.vercel.danfelogarporfolios.kt_todoapp.addtasks.data.TaskRepository
import app.vercel.danfelogarporfolios.kt_todoapp.addtasks.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    operator fun invoke(): Flow<List<TaskModel>> = taskRepository.tasks
}