package io.github.cloudyhug.cloudycook

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.cloudyhug.cloudycook.IngredientAndQuantityRecyclerViewAdapter
import io.github.cloudyhug.cloudycook.R

class ViewRecipeIngredientsFragment() : Fragment() {
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view = inflater.inflate(R.layout.fragment_view_recipe_ingredient_list, container, false)
    if (view is RecyclerView) {
      view.layoutManager = LinearLayoutManager(context)
      view.adapter =
        IngredientAndQuantityRecyclerViewAdapter(
          requireContext()
        )
    }
    return view
  }
}