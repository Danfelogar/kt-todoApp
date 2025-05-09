package app.vercel.danfelogarporfolios.kt_todoapp.addtasks.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import app.vercel.danfelogarporfolios.kt_todoapp.addtasks.ui.model.TaskModel

@Composable
fun TasksScreen(
    modifier: Modifier,
    tasksViewModel: TasksViewModel
) {
    val showDialog: Boolean by tasksViewModel.showDialog.observeAsState(false)
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    //state of alert every time
    val uiState by produceState<TasksUiState>(
        initialValue = TasksUiState.Loading,
        key1 = lifecycle,
        key2 = tasksViewModel
    ) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            tasksViewModel.uiState.collect { value = it }
        }
    }

    when (uiState) {
        is TasksUiState.Error -> {}
        TasksUiState.Loading -> {
            CircularProgressIndicator()
        }
        is TasksUiState.Success -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
            ) {
                AddTasksDialog(
                    show = showDialog,
                    onDismiss = { tasksViewModel.dialogClose() },
                    onTaskAdded = { tasksViewModel.onTaskCreated(it) }
                )
                FabDialog(
                    modifier = Modifier.align(Alignment.BottomEnd),
                    tasksViewModel = tasksViewModel
                )
                TaskList((uiState as TasksUiState.Success).tasks, tasksViewModel)
            }
        }
    }


}

@Composable
fun TaskList(tasks: List<TaskModel>, tasksViewModel: TasksViewModel) {
    LazyColumn(
        modifier = Modifier
    ) {
        items(tasks, key = { it.id }) { task ->
            ItemTask(
                taskModel = task,
                tasksViewModel = tasksViewModel
            )
        }
    }
}

@Composable
fun ItemTask(taskModel: TaskModel, tasksViewModel: TasksViewModel) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
//        border = BorderStroke(2.dp, Color.Magenta),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = { tasksViewModel.onItemRemove(taskModel) }
                )
            }

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = taskModel.task,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(horizontal = 4.dp)
            )
            Checkbox(
                checked = taskModel.selected,
                onCheckedChange = { tasksViewModel.onCheckBoxSelected(taskModel) }
            )
        }
    }
}

@Composable
fun FabDialog(
    modifier: Modifier = Modifier,
    tasksViewModel: TasksViewModel,
) {
    FloatingActionButton(
        onClick = { tasksViewModel.onShowDialogClick() },
        modifier = modifier
            .padding(16.dp)
    ) {
        Icon(Icons.Filled.Add, contentDescription = "")

    }
}


@Composable
fun AddTasksDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onTaskAdded: (String) -> Unit
) {
    var myTask by remember { mutableStateOf("") }
    if (show) {
        Dialog(
            onDismissRequest = { onDismiss() }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Add your task",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(16.dp))
                TextField(
                    value = myTask,
                    onValueChange = { myTask = it },
                    singleLine = true,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.size(16.dp))
                Button(
                    onClick = {
                        onTaskAdded(myTask)
                        myTask = ""
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Send Task"
                    )
                }
            }
        }
    }
}