package io.github.cloudyhug.cloudycook

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import io.github.cloudyhug.cloudycook.components.sync.SyncFragment
import io.github.cloudyhug.cloudycook.database.AppDatabase
import io.github.cloudyhug.cloudycook.database.DatabaseManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RecipeGroupChoiceDialogListener {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    
    Database.db =
      Room.databaseBuilder(applicationContext, AppDatabase::class.java, "db-recipe")
          .allowMainThreadQueries()
          .build()
    /*DatabaseManager.populateFromJson(Database.db!!, DatabaseManager.example)*/
    
    recipesButton.setOnClickListener { _ ->
      RecipeGroupChoiceDialogFragment(this).show(supportFragmentManager, "recipeGroupChoice")
    }
    
    ingredientsButton.setOnClickListener { _ ->
      val intent = Intent(this, IngredientsActivity::class.java)
      startActivity(intent)
    }
    
    syncButton.setOnClickListener { _ ->
      val intent = Intent(this, SyncActivity::class.java)
      startActivity(intent)
    }
  }
  
  override fun choiceMade(choice: Int) {
    val intent = Intent(this, RecipesActivity::class.java)
    intent.putExtra("recipeGroup", choice)
    startActivity(intent)
  }
}

// TODO : les recettes après la première ne s'affichent pas (corriger db manager ?)
// TODO : log partout
// TODO : db pas en main thread
// TODO : trier recettes alpha ou diff< ou diff>