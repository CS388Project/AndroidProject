package com.juansapplication.app.modules.gamesearchpage.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juansapplication.app.R
import com.juansapplication.app.databinding.RowListrectanglefifteenBinding
import com.juansapplication.app.modules.gamesearchpage.`data`.model.ListrectanglefifteenRowModel
import kotlin.Int
import kotlin.collections.List

class ListrectanglefifteenAdapter(
  var list: List<ListrectanglefifteenRowModel>
) : RecyclerView.Adapter<ListrectanglefifteenAdapter.RowListrectanglefifteenVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowListrectanglefifteenVH {
    val
        view=LayoutInflater.from(parent.context).inflate(R.layout.row_listrectanglefifteen,parent,false)
    return RowListrectanglefifteenVH(view)
  }

  override fun onBindViewHolder(holder: RowListrectanglefifteenVH, position: Int) {
    val listrectanglefifteenRowModel = ListrectanglefifteenRowModel()
    // TODO uncomment following line after integration with data source
    // val listrectanglefifteenRowModel = list[position]
    holder.binding.listrectanglefifteenRowModel = listrectanglefifteenRowModel
  }

  override fun getItemCount(): Int = 3
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<ListrectanglefifteenRowModel>) {
    list = newData
    notifyDataSetChanged()
  }

  fun setOnItemClickListener(clickListener: OnItemClickListener) {
    this.clickListener = clickListener
  }

  interface OnItemClickListener {
    fun onItemClick(
      view: View,
      position: Int,
      item: ListrectanglefifteenRowModel
    ) {
    }
  }

  inner class RowListrectanglefifteenVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowListrectanglefifteenBinding = RowListrectanglefifteenBinding.bind(itemView)
  }
}
