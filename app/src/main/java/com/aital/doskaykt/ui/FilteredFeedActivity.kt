package com.aital.doskaykt.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.aital.doskaykt.FilteredFeedViewModel
import com.aital.doskaykt.PostsAdapter
import com.aital.doskaykt.databinding.ActivityFilteredFeedBinding
import com.aital.doskaykt.models.Category
import com.aital.doskaykt.models.Post
import com.aital.doskaykt.models.Rubric
import com.aital.doskaykt.models.Subcategory

class FilteredFeedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFilteredFeedBinding

    private lateinit var viewModel: FilteredFeedViewModel
    private lateinit var postsAdapter : PostsAdapter

    private var category: Category? = null
    private var subcategory: Subcategory? = null
    private var rubric: Rubric? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFilteredFeedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent.hasExtra("category")) {
            category = intent.getSerializableExtra("category") as Category
        }

        if (intent.hasExtra("subcategory")) {
            subcategory = intent.getSerializableExtra("subcategory") as Subcategory
        }

        if (intent.hasExtra("rubric")) {
            rubric = intent.getSerializableExtra("rubric") as Rubric
        }

        buildFilterText()

        prepareRecyclerView()
        viewModel = ViewModelProvider(this)[FilteredFeedViewModel::class.java]
        viewModel.getFilteredFeed(category?.id, subcategory?.id, rubric?.id)
        viewModel.observePostsLiveData().observe(this, Observer { postsList ->
            postsAdapter.setPostsList(postsList)
        })
    }

    private fun prepareRecyclerView() {
        postsAdapter = PostsAdapter()

        binding.rvPosts.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = postsAdapter
        }

        postsAdapter.setOnClickListener(object :
            PostsAdapter.OnClickListener {
            override fun onClick(position: Int, model: Post) {
                val intent = Intent(baseContext, PostActivity::class.java)
                intent.putExtra("post_data", model)
                startActivity(intent)
            }
        })
    }

    private fun buildFilterText() {
        if (category != null) {
            binding.tvCategoryName.text = category!!.name
        } else {
            binding.tvCategoryName.text = "Все категории"
        }

        if (subcategory != null) {
            binding.tvSubcategoryName.text = subcategory!!.name
        } else {
            binding.tvSubcategoryName.text = "Все подкатегории"
        }

        if (rubric != null) {
            binding.tvRubricName.text = rubric!!.name
        } else {
            binding.tvRubricName.text = "Все рубрики"
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            this.finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
