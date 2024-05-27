package com.aital.doskaykt.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.aital.doskaykt.FilteredFeedViewModel
import com.aital.doskaykt.PostsAdapter
import com.aital.doskaykt.databinding.ActivityFilteredFeedBinding
import com.aital.doskaykt.models.Category
import com.aital.doskaykt.models.Post

class FilteredFeedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFilteredFeedBinding

    private lateinit var viewModel: FilteredFeedViewModel
    private lateinit var postsAdapter : PostsAdapter

    private var categoryId = -1
    private var subcategoryId = -1
    private var rubricId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFilteredFeedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        categoryId = intent.getIntExtra("categoryId", -1)
        subcategoryId = intent.getIntExtra("subcategoryId", -1)
        rubricId = intent.getIntExtra("rubricId", -1)

        prepareRecyclerView()
        viewModel = ViewModelProvider(this)[FilteredFeedViewModel::class.java]
        println(categoryId)
        println(subcategoryId)
        println(rubricId)
        viewModel.getFilteredFeed(categoryId, subcategoryId, rubricId)
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
}
