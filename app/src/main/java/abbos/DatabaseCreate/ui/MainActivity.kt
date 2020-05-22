package abbos.DatabaseCreate.ui

import abbos.DatabaseCreate.R
import abbos.DatabaseCreate.database.model.MyModel
import abbos.DatabaseCreate.common.isNotClear
import abbos.DatabaseCreate.common.mytrim
import abbos.uzeu.database.DatabaseProvider
import android.content.ClipData
import android.content.ClipboardManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var list: ArrayList<MyModel>? = null
    private val adapter: MyAdapter by lazy { MyAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        list = ArrayList()
        edt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                btn_create.isEnabled = edt.isNotClear()
                btn_save.isEnabled = false
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        btn_assets.setOnClickListener {
            assets.open("data.txt").bufferedReader().use { edt.setText(it.readText()) }
        }
        btn_put.setOnClickListener { edt.setText(getTextFromClipboard()) }
        btn_clear.setOnClickListener { edt.setText("") }
        btn_create.setOnClickListener { create(edt.text.toString()) }
        btn_save.setOnClickListener { save() }
        rv.adapter = adapter
    }

    private fun create(text: String) {
        btn_save.isEnabled = true
        list?.clear()
        var tmpList: ArrayList<String> = ArrayList()
        var t = ""
        text.forEach {
            t += it
            if (it == '\n') {
                tmpList.add(t.mytrim())
                t = ""
            }
        }
        for (i in 0..tmpList.size / 6 - 1) {
            list?.add(
                MyModel(
                    question = tmpList[i * 6],
                    answer = tmpList[6 * i + 1],
                    error1 = tmpList[6 * i + 2],
                    error2 = tmpList[6 * i + 3],
                    error3 = tmpList[6 * i + 4],
                    lowerquestion = tmpList[6 * i].toLowerCase(),
                    loweranswer = tmpList[6 * i + 1].toLowerCase(),
                    lowererror1 = tmpList[6 * i + 2].toLowerCase(),
                    lowererror2 = tmpList[6 * i + 3].toLowerCase(),
                    lowererror3 = tmpList[6 * i + 4].toLowerCase()
                )
            )
        }
        adapter.setNewList(list ?: arrayListOf())
        Toast.makeText(this, "${list?.size}", Toast.LENGTH_LONG).show()
    }

    private fun save() {
        list?.forEach { DatabaseProvider.instance(this).databaseDao().insertModel(it) }
        Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show()
    }

    fun copyTextToClipboard(text: String) {
        val myClipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val myClip = ClipData.newPlainText("text", text);
        myClipboard.setPrimaryClip(myClip);
    }

    fun getTextFromClipboard(): String {
        val myClipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val abc = myClipboard?.getPrimaryClip()
        val item = abc?.getItemAt(0)
        return item?.text.toString()
    }
}
