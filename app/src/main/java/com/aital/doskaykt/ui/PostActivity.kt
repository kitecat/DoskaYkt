package com.aital.doskaykt.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.aital.doskaykt.databinding.ActivityPostBinding
import com.aital.doskaykt.models.Post
import com.bumptech.glide.Glide


class PostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var post: Post? = null

        if (intent.hasExtra("post_data")) {
            post = intent.getSerializableExtra("post_data") as Post
        }

        if (post != null) {
            Glide.with(this)
                .load(post.picsUrl[0].url)
                .into(binding.postPic)
            binding.postTitle.text = post.title
            binding.postPrice.text = post.price
            binding.postText.text = post.text
            binding.postDate.text = post.publicDate
            binding.postFavs.text = post.favorites.toString()
            binding.postViews.text = post.views.toString()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            this.finish()
        }
        return super.onOptionsItemSelected(item)
    }
}