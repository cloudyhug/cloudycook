package io.github.cloudyhug.cloudycook.components

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.github.cloudyhug.cloudycook.R

class StringRecyclerViewAdapter(
  private val values: List<String>
) : RecyclerView.Adapter<StringRecyclerViewAdapter.ViewHolder>() {
  
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context)
      .inflate(R.layout.fragment_step_listitem, parent, false)
    return ViewHolder(view)
  }
  
  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val item = values[position]
    holder.itemNumberView.text = (position + 1).toString()
    holder.contentView.text = item
  }
  
  override fun getItemCount(): Int = values.size
  
  inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val itemNumberView: TextView = view.findViewById(R.id.item_number)
    val contentView: TextView = view.findViewById(R.id.content)
    
    override fun toString(): String {
      return super.toString() + " '" + contentView.text + "'"
    }
  }
}