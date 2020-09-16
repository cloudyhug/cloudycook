package io.github.cloudyhug.cloudycook

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.DialogFragment

class SettingsValidationErrorDialogFragment(
  val title: String,
  val text: String
) : DialogFragment() {
  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    return activity?.let {
      val view = it.layoutInflater.inflate(R.layout.dialog_settings_validation_error, null)
    
      view.findViewById<TextView>(R.id.dialogTitle).text = title
      view.findViewById<TextView>(R.id.dialogText).text = text
      view.findViewById<TextView>(R.id.dialogPositiveButton)?.setOnClickListener { _ -> dismiss() }
    
      AlertDialog.Builder(it)
        .setView(view)
        .create()
    
    } ?: throw IllegalStateException("Activity cannot be null")
  }
}