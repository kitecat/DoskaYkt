package com.aital.doskaykt.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aital.doskaykt.CategoriesAdapter
import com.aital.doskaykt.CategoriesViewModel
import com.aital.doskaykt.databinding.ActivityCategoriesBinding
import com.aital.doskaykt.models.Category

class CategoriesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoriesBinding

    private lateinit var viewModel: CategoriesViewModel
    private lateinit var categoriesAdapter : CategoriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var category: Category? = null

        prepareRecyclerView()
        viewModel = ViewModelProvider(this)[CategoriesViewModel::class.java]
        viewModel.getCategories()
        viewModel.observeCategoriesLiveData().observe(this, Observer { categoriesList ->
            categoriesAdapter.setCategoriesList(categoriesList)
        })
    }

    private fun prepareRecyclerView() {
        categoriesAdapter = CategoriesAdapter()

        binding.rvCategories.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = categoriesAdapter
        }

        categoriesAdapter.setOnClickListener(object :
            CategoriesAdapter.OnClickListener {
            override fun onClick(position: Int, model: Category) {
                if (model.subcategories != null) {
                    if (model.subcategories.isNotEmpty()) {
                        val intent = Intent(baseContext, SubcategoriesActivity::class.java)
                        intent.putExtra("category", model)
                        intent.putExtra("categoryId", model.id)
                        startActivity(intent)
                    }
                }
//                val intent = Intent(context, PostActivity::class.java)
//                // Passing the data to the
//                // EmployeeDetails Activity
//                intent.putExtra("post_data", model)
//                startActivity(intent)
            }
        })
    }
}
