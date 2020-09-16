package io.github.cloudyhug.cloudycook

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope

class LoadingDialogFragment(val message: String) : DialogFragment() {
  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    return activity?.let {
      val view = it.layoutInflater.inflate(R.layout.dialog_loading, null)
      
      view.findViewById<TextView>(R.id.dialogMessage).text = message
      
      AlertDialog.Builder(it)
        .setView(view)
        .create()
      
    } ?: throw IllegalStateException("Activity cannot be null")
  }
}