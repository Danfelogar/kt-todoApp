package app.vercel.danfelogarporfolios.kt_todoapp.addtasks.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class TaskEntity(
//    @PrimaryKey(autoGenerate =  true)
    @PrimaryKey()
    val id: Int,
    var task: String,
    var selected: Boolean = false
)