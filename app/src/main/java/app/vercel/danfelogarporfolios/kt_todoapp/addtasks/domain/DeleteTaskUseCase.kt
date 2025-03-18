package app.vercel.danfelogarporfolios.kt_todoapp.addtasks.domain

import app.vercel.danfelogarporfolios.kt_todoapp.addtasks.data.TaskRepository
import app.vercel.danfelogarporfolios.kt_todoapp.addtasks.ui.model.TaskModel
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {

    suspend operator  fun invoke(taskModel: TaskModel){
        taskRepository.delete(taskModel)
    }
}