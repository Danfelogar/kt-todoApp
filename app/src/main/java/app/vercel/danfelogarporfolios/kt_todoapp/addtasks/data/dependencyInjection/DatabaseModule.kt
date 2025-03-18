package app.vercel.danfelogarporfolios.kt_todoapp.addtasks.data.dependencyInjection

import android.content.Context
import androidx.room.Room
import app.vercel.danfelogarporfolios.kt_todoapp.addtasks.data.TaskDao
import app.vercel.danfelogarporfolios.kt_todoapp.addtasks.data.TodoDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideTaskData(todoDatabase: TodoDataBase):TaskDao{
        return todoDatabase.taskDao()
    }

    @Provides
    @Singleton
    fun provideTodoDatabase(@ApplicationContext appContext: Context): TodoDataBase {
        return Room
            .databaseBuilder(appContext, TodoDataBase::class.java, "TaskDatabase")
            .build()
    }
}