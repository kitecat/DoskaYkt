package com.aital.doskaykt.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.aital.doskaykt.PostsAdapter
import com.aital.doskaykt.PostsViewModel
import com.aital.doskaykt.databinding.FragmentHomeBinding
import com.aital.doskaykt.models.Post
import com.aital.doskaykt.ui.CategoriesActivity
import com.aital.doskaykt.ui.PostActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var viewModel: PostsViewModel
    private lateinit var postsAdapter : PostsAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        prepareRecyclerView()
        viewModel = ViewModelProvider(this)[PostsViewModel::class.java]
        viewModel.getFeed()
        viewModel.observePostsLiveData().observe(viewLifecycleOwner, Observer { postsList ->
            postsAdapter.setPostsList(postsList)
        })

        binding.btnCategories.setOnClickListener {
            val intent = Intent(context, CategoriesActivity::class.java)
//            intent.putExtra("post_data", model)
            startActivity(intent)
        }
        return root
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
                val intent = Intent(context, PostActivity::class.java)
                intent.putExtra("post_data", model)
                startActivity(intent)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}