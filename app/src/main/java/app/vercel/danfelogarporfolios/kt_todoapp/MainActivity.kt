package app.vercel.danfelogarporfolios.kt_todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import app.vercel.danfelogarporfolios.kt_todoapp.addtasks.ui.DanfelogarComponent
import app.vercel.danfelogarporfolios.kt_todoapp.addtasks.ui.TasksScreen
import app.vercel.danfelogarporfolios.kt_todoapp.addtasks.ui.TasksViewModel
import app.vercel.danfelogarporfolios.kt_todoapp.ui.theme.KtTodoAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val tasksViewModel:TasksViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KtTodoAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    TasksScreen(
//                        modifier = Modifier.padding(innerPadding),
//                        tasksViewModel = tasksViewModel
//                    )
                    DanfelogarComponent()
                }
            }
        }
    }
}
