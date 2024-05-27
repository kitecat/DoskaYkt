package com.aital.doskaykt.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.aital.doskaykt.SubcategoriesAdapter
import com.aital.doskaykt.databinding.ActivitySubcategoriesBinding
import com.aital.doskaykt.models.Category
import com.aital.doskaykt.models.Subcategory

class SubcategoriesActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySubcategoriesBinding

    private lateinit var subcategoriesAdapter : SubcategoriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySubcategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var category: Category? = null

        if (intent.hasExtra("category")) {
            category = intent.getSerializableExtra("category") as Category
        }

        if (category != null) {
            subcategoriesAdapter = SubcategoriesAdapter()
            category.subcategories?.let { subcategoriesAdapter.setSubcategoriesList(it) }
            binding.rvSubcategories.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = subcategoriesAdapter
            }
        }

        subcategoriesAdapter.setOnClickListener(object :
            SubcategoriesAdapter.OnClickListener {
            override fun onClick(position: Int, model: Subcategory) {
                if (model.rubrics != null) {
                    if (model.rubrics.isNotEmpty()) {
                        val intent = Intent(baseContext, RubricsActivity::class.java)
                        if (category != null) {
                            intent.putExtra("categoryId", category.id)
                        }
                        intent.putExtra("subcategory", model)
                        startActivity(intent)
                    }
                }
            }
        })
    }
}
