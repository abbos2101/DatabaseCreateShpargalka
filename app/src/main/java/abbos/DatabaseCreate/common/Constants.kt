package abbos.DatabaseCreate.common

import android.widget.EditText

val baseName = "data.db"

fun EditText.isNotClear() = this.text.toString().trim().isNotEmpty()

fun String.mytrim() = this.trim()