package io.github.cloudyhug.cloudycook.components.sync

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import io.github.cloudyhug.cloudycook.R

class SyncFragment : PreferenceFragmentCompat() {
  
  override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
    setPreferencesFromResource(R.xml.root_preferences, rootKey)
  }
}
// TODO : trier recettes alpha ou diff< ou diff> / ip / port