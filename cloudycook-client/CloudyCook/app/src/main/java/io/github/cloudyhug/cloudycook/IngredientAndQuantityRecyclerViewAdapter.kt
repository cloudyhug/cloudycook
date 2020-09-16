package io.github.cloudyhug.cloudycook

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat

class IngredientAndQuantityRecyclerViewAdapter(
  private val context: Context
) : RecyclerView.Adapter<IngredientAndQuantityRecyclerViewAdapter.ViewHolder>() {
  private val values = ViewRecipeData.ingredients!!
  
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context)
      .inflate(R.layout.fragment_view_recipe_ingredient_listitem, parent, false)
    return ViewHolder(view)
  }
  
  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val (item, quantity) = values[position]
    holder.quantityTextView.text = quantity
    holder.ingredientTextView.text = item.name
    holder.ingredientTextView.setCompoundDrawables(
      ContextCompat.getDrawable(context, IngredientCategory.toIcon(item.category)),
      null,
      null,
      null
    )
  }
  
  override fun getItemCount(): Int = values.size
  
  inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val quantityTextView: TextView = view.findViewById(R.id.quantityTextView)
    val ingredientTextView: TextView = view.findViewById(R.id.ingredientTextView2)
    
    override fun toString(): String {
      return super.toString() + " '${ingredientTextView.text} (${quantityTextView.text})'"
    }
  }
}