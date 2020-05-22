package abbos.DatabaseCreate.ui

import abbos.DatabaseCreate.R
import abbos.DatabaseCreate.database.model.MyModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    private var list: ArrayList<MyModel>? = null

    class ViewHolder : RecyclerView.ViewHolder {
        var tv_q: TextView? = null
        var tv_a: TextView? = null
        var tv_b: TextView? = null
        var tv_c: TextView? = null
        var tv_d: TextView? = null

        constructor(itemView: View) : super(itemView) {
            this.tv_q = itemView.findViewById(R.id.tv_q)
            this.tv_a = itemView.findViewById(R.id.tv_a)
            this.tv_b = itemView.findViewById(R.id.tv_b)
            this.tv_c = itemView.findViewById(R.id.tv_c)
            this.tv_d = itemView.findViewById(R.id.tv_d)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = this.list?.get(position)
        holder.tv_q?.setText("${position + 1}. ${model?.question}")
        holder.tv_a?.setText("*) ${model?.answer}")
        holder.tv_b?.setText("B) ${model?.error1}")
        holder.tv_c?.setText("C) ${model?.error2}")
        holder.tv_d?.setText("D) ${model?.error3}")
    }

    fun setNewList(newList: ArrayList<MyModel>) {
        this.list = newList
        this.notifyDataSetChanged()
    }
}