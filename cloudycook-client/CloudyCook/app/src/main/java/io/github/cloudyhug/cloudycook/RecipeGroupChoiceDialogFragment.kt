package io.github.cloudyhug.cloudycook

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment

class RecipeGroupChoiceDialogFragment(val listener: RecipeGroupChoiceDialogListener) : DialogFragment() {
  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    return activity?.let {
      val view = it.layoutInflater.inflate(R.layout.dialog_recipe_group_choice, null)
  
      view.findViewById<Button>(R.id.startersButton)?.setOnClickListener { _ ->
        dismiss()
        listener.choiceMade(RecipeGroup.Starters)
      }
      view.findViewById<Button>(R.id.mainCoursesButton)?.setOnClickListener { _ ->
        dismiss()
        listener.choiceMade(RecipeGroup.MainCourses)
      }
      view.findViewById<Button>(R.id.dessertsButton)?.setOnClickListener { _ ->
        dismiss()
        listener.choiceMade(RecipeGroup.Desserts)
      }
      
      AlertDialog.Builder(it)
        .setView(view)
        .create()
      
    } ?: throw IllegalStateException("Activity cannot be null")
  }
}