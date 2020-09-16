package io.github.cloudyhug.cloudycook.components.addrecipe

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import io.github.cloudyhug.cloudycook.R
import kotlinx.android.synthetic.main.add_step_dialog_content.*

class AddStepDialogFragment : DialogFragment() {
  lateinit var listener: AddStepDialogListener
  
  override fun onAttach(context: Context) {
    super.onAttach(context)
    listener = context as AddStepDialogListener
  }
  
  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    return requireActivity().let {
      val builder = AlertDialog.Builder(it)
      builder
        .setTitle("What is the new step?")
        .setView(R.layout.add_step_dialog_content)
        .setNegativeButton("Cancel") { _, _ -> Unit }
        .setPositiveButton("Add step") { _, _ ->
          if (editStep.text.isNotBlank()) {
            listener.onDialogPositiveClick(editStep.text.toString())
          }
        }
      builder.create()
    }
  }
  
  interface AddStepDialogListener {
    fun onDialogPositiveClick(newStep: String)
  }
}