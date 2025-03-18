package app.vercel.danfelogarporfolios.kt_todoapp.addtasks.ui

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.vercel.danfelogarporfolios.kt_todoapp.addtasks.domain.AddTaskUseCase
import app.vercel.danfelogarporfolios.kt_todoapp.addtasks.domain.DeleteTaskUseCase
import app.vercel.danfelogarporfolios.kt_todoapp.addtasks.domain.GetTasksUseCase
import app.vercel.danfelogarporfolios.kt_todoapp.addtasks.domain.UpdateTaskUseCase
import app.vercel.danfelogarporfolios.kt_todoapp.addtasks.ui.TasksUiState.*
import app.vercel.danfelogarporfolios.kt_todoapp.addtasks.ui.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    getTasksUseCase: GetTasksUseCase
):ViewModel()  {

    val uiState: StateFlow<TasksUiState> = getTasksUseCase().map( ::Success )
        .catch { Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TasksUiState.Loading)

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog:LiveData<Boolean> = _showDialog

//    private val _tasks = mutableStateListOf<TaskModel>()
//    val tasks:List<TaskModel> = _tasks

    fun dialogClose() {
        _showDialog.value = false
    }

    fun onTaskCreated(task: String) {
        _showDialog.value = false

        viewModelScope.launch {
            addTaskUseCase(TaskModel(task = task))
        }
    }

    fun onShowDialogClick() {
        _showDialog.value = true
    }

    fun onCheckBoxSelected(taskModel: TaskModel) {
//        val idx = _tasks.indexOf(taskModel)
//        //create a new object but with that new param
//        _tasks[idx] = _tasks[idx].let {
//            it.copy(selected = !it.selected)
//        }

        viewModelScope.launch {
            updateTaskUseCase(taskModel.copy(selected =  !taskModel.selected))
        }
    }

    fun onItemRemove(taskModel: TaskModel) {
//        val task = _tasks.find { it.id == taskModel.id }
//        _tasks.remove(task)
        deleteTaskUseCase
        viewModelScope.launch {
            deleteTaskUseCase(taskModel)
        }

    }
}