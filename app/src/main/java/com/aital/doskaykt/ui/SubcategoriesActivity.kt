package com.aital.doskaykt.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
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

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var category: Category? = null

        if (intent.hasExtra("category")) {
            category = intent.getSerializableExtra("category") as Category
        }

        // показ всех объявлений определенной категории
        binding.tvAllSubcategories.setOnClickListener {
            val intent = Intent(baseContext, FilteredFeedActivity::class.java)
            if (category != null) {
                intent.putExtra("category", category)
            }
            startActivity(intent)
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
                            intent.putExtra("category", category)
                        }
                        intent.putExtra("subcategory", model)
                        startActivity(intent)
                    }
                }
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            this.finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
