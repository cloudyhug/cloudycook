package io.github.cloudyhug.cloudycook.components.addrecipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.cloudyhug.cloudycook.R
import io.github.cloudyhug.cloudycook.components.StringRecyclerViewAdapter

class AddStepsFragment : Fragment() {
  val items: MutableList<String> = ArrayList()
  
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view = inflater.inflate(R.layout.fragment_step_list, container, false)
    if (view is RecyclerView) {
      view.layoutManager = LinearLayoutManager(context)
      view.adapter = StringRecyclerViewAdapter(items)
    }
    return view
  }
}