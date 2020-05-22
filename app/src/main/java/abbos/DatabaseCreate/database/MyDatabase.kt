package abbos.uzeu.database

import abbos.DatabaseCreate.database.model.MyModel
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MyModel::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun databaseDao(): DatabaseDao
}