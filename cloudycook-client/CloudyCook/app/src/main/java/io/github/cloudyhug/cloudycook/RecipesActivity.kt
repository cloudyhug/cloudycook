package io.github.cloudyhug.cloudycook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.cloudyhug.cloudycook.database.RecipeEntry

class RecipesActivity : AppCompatActivity() {
  private lateinit var items: List<RecipeEntry>
  
  private lateinit var recyclerView: RecyclerView
  private lateinit var viewAdapter: RecyclerView.Adapter<*>
  private lateinit var viewManager: RecyclerView.LayoutManager
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_recipes)
  
    val recipeGroup = intent.getIntExtra("recipeGroup", RecipeGroup.MainCourses)
    supportActionBar?.title = RecipeGroup.toString(recipeGroup)
    items = Database.db!!.recipeEntryDao().findByGroup(recipeGroup)
    
    viewManager = LinearLayoutManager(this)
    viewAdapter =
      RecipeRecyclerViewAdapter(items, this)
    
    recyclerView = findViewById<RecyclerView>(R.id.recipes_list).apply {
      layoutManager = viewManager
      adapter = viewAdapter
    }
  }
}