package app.vercel.danfelogarporfolios.kt_todoapp.addtasks.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TaskEntity::class], version = 1)
abstract class TodoDataBase:RoomDatabase() {
//need one model for persist in dataBase "model to data not to ui"

    //DAO: this line create data base
    abstract fun taskDao():TaskDao
}