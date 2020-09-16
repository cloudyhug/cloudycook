package io.github.cloudyhug.cloudycook

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import io.github.cloudyhug.cloudycook.database.IngredientEntry

class IngredientRecyclerViewAdapter(
  private val values: List<IngredientEntry>,
  private val context: Context
) : RecyclerView.Adapter<IngredientRecyclerViewAdapter.ViewHolder>() {
  
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context)
      .inflate(R.layout.ingredient_list_item, parent, false)
    return ViewHolder(view)
  }
  
  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val item = values[position]
    holder.ingredientTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(
      AppCompatResources.getDrawable(context, IngredientCategory.toIcon(item.category)),
      null,
      null,
      null)
    holder.ingredientTextView.compoundDrawablePadding =
      Math.round(context.resources.displayMetrics.density * 15.0f)
    holder.ingredientTextView.text = item.name
  }
  
  override fun getItemCount(): Int = values.size
  
  inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val ingredientTextView: TextView = view.findViewById(R.id.ingredientTextView)
    
    override fun toString(): String {
      return super.toString() + " '" + ingredientTextView.text + "'"
    }
  }
}