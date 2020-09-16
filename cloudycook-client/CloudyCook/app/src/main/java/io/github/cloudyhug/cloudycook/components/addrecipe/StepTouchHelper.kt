package io.github.cloudyhug.cloudycook.components.addrecipe

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class StepTouchHelper : ItemTouchHelper.SimpleCallback(
  ItemTouchHelper.UP or ItemTouchHelper.DOWN,
  ItemTouchHelper.RIGHT
) {
  override fun onMove(
    recyclerView: RecyclerView,
    viewHolder: RecyclerView.ViewHolder,
    target: RecyclerView.ViewHolder
  ): Boolean {
    TODO("Not yet implemented")
  }
  
  override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
    TODO("Not yet implemented")
  }
  
}