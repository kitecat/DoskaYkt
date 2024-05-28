package com.aital.doskaykt.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.aital.doskaykt.RubricsAdapter
import com.aital.doskaykt.databinding.ActivityRubricsBinding
import com.aital.doskaykt.models.Category
import com.aital.doskaykt.models.Rubric
import com.aital.doskaykt.models.Subcategory

class RubricsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRubricsBinding

    private lateinit var rubricsAdapter : RubricsAdapter

    private var category: Category? = null
    private var subcategory: Subcategory? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRubricsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent.hasExtra("category")) {
            category = intent.getSerializableExtra("category") as Category
        }

        if (intent.hasExtra("subcategory")) {
            subcategory = intent.getSerializableExtra("subcategory") as Subcategory
        }

        // показ всех объявлений определенной категории и подкатегории
        binding.tvAllRubrics.setOnClickListener {
            val intent = Intent(baseContext, FilteredFeedActivity::class.java)
            if (category != null) {
                intent.putExtra("category", category)
            }
            if (subcategory != null) {
                intent.putExtra("subcategory", subcategory!!)
            }
            startActivity(intent)
        }

        if (subcategory != null) {
            rubricsAdapter = RubricsAdapter()
            subcategory!!.rubrics?.let { rubricsAdapter.setRubricsList(it) }
            binding.rvRubrics.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = rubricsAdapter
            }
        }

        rubricsAdapter.setOnClickListener(object :
            RubricsAdapter.OnClickListener {
            override fun onClick(position: Int, model: Rubric) {
                val intent = Intent(baseContext, FilteredFeedActivity::class.java)
                if (category != null) {
                    intent.putExtra("category", category)
                }
                if (subcategory != null) {
                    intent.putExtra("subcategory", subcategory!!)
                }
                intent.putExtra("rubric", model)
                startActivity(intent)
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
