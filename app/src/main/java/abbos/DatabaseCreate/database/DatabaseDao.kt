package abbos.uzeu.database

import abbos.DatabaseCreate.database.model.MyModel
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DatabaseDao {
    @Insert
    fun insertModel(model:MyModel)
}