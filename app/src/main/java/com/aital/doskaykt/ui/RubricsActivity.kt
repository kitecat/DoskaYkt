package com.aital.doskaykt.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.aital.doskaykt.RubricsAdapter
import com.aital.doskaykt.databinding.ActivityRubricsBinding
import com.aital.doskaykt.models.Rubric
import com.aital.doskaykt.models.Subcategory

class RubricsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRubricsBinding

    private lateinit var rubricsAdapter : RubricsAdapter

    private var categoryId: Int? = null
    private var subcategory: Subcategory? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRubricsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra("categoryId")) {
            categoryId = intent.getIntExtra("categoryId", -1)
        }

        if (intent.hasExtra("subcategory")) {
            subcategory = intent.getSerializableExtra("subcategory") as Subcategory
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
                if (categoryId != null) {
                    intent.putExtra("categoryId", categoryId)
                }
                if (subcategory != null) {
                    intent.putExtra("subcategoryId", subcategory!!.id)
                }
                intent.putExtra("rubricId", model.id)
                startActivity(intent)
            }
        })
    }
}
