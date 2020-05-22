package abbos.DatabaseCreate.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class MyModel(
    @PrimaryKey(autoGenerate = true)
    var _id: Long = 0,
    var question: String? = null,
    var answer: String? = null,
    var error1: String? = null,
    var error2: String? = null,
    var error3: String? = null,
    var lowerquestion: String? = null,
    var loweranswer: String? = null,
    var lowererror1: String? = null,
    var lowererror2: String? = null,
    var lowererror3: String? = null
)