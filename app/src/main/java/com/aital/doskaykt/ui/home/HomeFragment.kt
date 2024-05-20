package com.aital.doskaykt.ui.home

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

class HomeFragment : Fragment() {

//    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: PostsViewModel
    private lateinit var postsAdapter : PostsAdapter

    private var _binding: FragmentHomeBinding? = null

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

//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
        prepareRecyclerView()
        viewModel = ViewModelProvider(this)[PostsViewModel::class.java]
        viewModel.getFeed()
        viewModel.observePostsLiveData().observe(viewLifecycleOwner, Observer { postsList ->
            postsAdapter.setPostsList(postsList)
        })
        return root
    }

    private fun prepareRecyclerView() {
        postsAdapter = PostsAdapter()
        binding.rvPosts.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = postsAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}