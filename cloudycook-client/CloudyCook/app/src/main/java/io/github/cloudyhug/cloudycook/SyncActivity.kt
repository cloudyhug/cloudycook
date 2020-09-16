package io.github.cloudyhug.cloudycook

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.EditTextPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.google.android.material.snackbar.Snackbar
import io.github.cloudyhug.cloudycook.database.DatabaseManager
import kotlinx.android.synthetic.main.activity_sync.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.HttpUrl
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import kotlin.NumberFormatException

class SyncActivity : AppCompatActivity() {
  private val TAG: String = "SyncActivity"
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_sync)
    supportFragmentManager
      .beginTransaction()
      .replace(R.id.settings, SettingsFragment())
      .commit()
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    
    syncButton.setOnClickListener { _ ->
      val pref = PreferenceManager.getDefaultSharedPreferences(this)
      val addr = pref.getString("remote_addr", "")!!
      val port = pref.getString("remote_port", "8080")!!.toInt()
      
      Log.d(TAG, "addr = ${addr}, port = ${port}")
      
      if (addr.isEmpty()) {
        Snackbar.make(
          constraintLayout,
          "L'adresse du serveur n'a pas été configurée.",
          Snackbar.LENGTH_LONG
        )
        .show()
        return@setOnClickListener
      }
      
      val url = HttpUrl.Builder()
        .scheme("http")
        .host(addr)
        .port(port)
        .build()
      
      Log.d(TAG, "before service creation")
      
      val service = Retrofit.Builder()
        .baseUrl(url)
        .build()
        .create(RemoteDatabaseService::class.java)
      
      Log.d(TAG, "service created")
      
      val loadingDialog = LoadingDialogFragment("Communication avec le serveur...")
      loadingDialog.show(supportFragmentManager, "loading")
      
      Log.d(TAG, "dialog launched")
/*      runBlocking {
        val x = service.getRecipes()
        Log.d(TAG, x.isSuccessful.toString())
      }*/
      
      GlobalScope.launch(Dispatchers.IO) {
        Log.d(TAG, "coroutine started")
        var response: Response<ResponseBody>?
        try {
          response = service.getRecipes()
          Log.d(TAG, "request sent")
        } catch (e: Exception) {
          Log.d(TAG, "request error : ${e.message}")
          response = null
        }
        
        response?.let {
          if (it.isSuccessful) {
            val jsonStr = response.body()!!.string()
            Log.d(TAG, "request successful : ${jsonStr.take(100)}[...]")
            DatabaseManager.populateFromJson(Database.db!!, jsonStr)
            GlobalScope.launch(Dispatchers.Main) {
              Snackbar
                .make(constraintLayout, "Base de données mise à jour.", Snackbar.LENGTH_LONG)
                .show()
            }
            loadingDialog.dismiss()
            return@launch
          }
        }
        
        Log.d(TAG, "request failed : ${response}")
        GlobalScope.launch(Dispatchers.Main) {
          Snackbar
            .make(constraintLayout, "Connexion au serveur impossible.", Snackbar.LENGTH_LONG)
            .show()
        }
        loadingDialog.dismiss()
      }
    }
  }
  
  class SettingsFragment : PreferenceFragmentCompat() {
    fun isIPAddress(str: String): Boolean {
      try {
        str.split('.').let {
          return (it.size == 4 && it.map { it.toInt() }.all { it in 0..255 })
        }
      } catch (_: Exception) {
        return false
      }
    }
    
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
      setPreferencesFromResource(R.xml.root_preferences, rootKey)
  
      preferenceScreen
        .findPreference<EditTextPreference>("remote_addr")
        ?.setOnPreferenceChangeListener { preference, newValue ->
          val str = newValue as String
          if (!str.matches(
                """^((?!-)[A-Za-z0-9-]{1,63}(?<!-)\.)+[A-Za-z]{2,6}${'$'}""".toRegex()
              ) && (!isIPAddress(str))) {
            SettingsValidationErrorDialogFragment(
              "Adresse invalide",
              "L'adresse doit être une IP (ex : 127.0.0.1) ou un nom de domaine (ex : google.com)."
            ).show(parentFragmentManager, "settingsValidationError")
            false
          } else {
            true
          }
        }
  
      preferenceScreen
        .findPreference<EditTextPreference>("remote_port")
        ?.setOnPreferenceChangeListener { preference, newValue ->
          try {
            // cast to Int can fail, range check throws same exception type: NumberFormatException
            if (!((newValue as String).toInt() in 0..65535)) {
              throw NumberFormatException()
            } else {
              true
            }
          } catch (e: NumberFormatException) {
            SettingsValidationErrorDialogFragment(
              "Port invalide",
              "Le port doit être un nombre entier compris entre 0 et 65535."
            ).show(parentFragmentManager, "settingsValidationError")
            false
          }
        }
    }
  }
}