package io.github.cloudyhug.cloudycook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.cloudyhug.cloudycook.database.IngredientEntry

class IngredientsActivity : AppCompatActivity() {
  private lateinit var items: List<IngredientEntry>
  
  private lateinit var recyclerView: RecyclerView
  private lateinit var viewAdapter: RecyclerView.Adapter<*>
  private lateinit var viewManager: RecyclerView.LayoutManager
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_ingredients)
    
    items = Database.db!!.ingredientEntryDao().getAll()
    
    viewManager = LinearLayoutManager(this)
    viewAdapter =
      IngredientRecyclerViewAdapter(items, this)
    
    recyclerView = findViewById<RecyclerView>(R.id.ingredients_list).apply {
      layoutManager = viewManager
      adapter = viewAdapter
    }
  }
}