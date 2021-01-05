package io.grocerieslist.groceries.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.grocerieslist.groceries.R
import io.grocerieslist.groceries.data.Item
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var itemList = emptyList<Item>()
    class MyViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = itemList[position]
       // holder.itemView.id_view.text = currentItem.id.toString()
        holder.itemView.item_view.text = currentItem.name.toString()
        holder.itemView.quantity_view.text = currentItem.quantity.toString()
    }
    fun setData(item: List<Item>) {
        this.itemList = item
        notifyDataSetChanged()

    }

}