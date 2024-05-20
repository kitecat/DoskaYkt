package com.aital.doskaykt

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostsViewModel : ViewModel() {
    private var postsLiveData = MutableLiveData<List<Post>>()
    fun getFeed() {
        RetrofitInstance.api.getFeed("", "", "").enqueue(object  : Callback<Posts> {
            override fun onResponse(call: Call<Posts>, response: Response<Posts>) {
                if (response.body()!=null){
                    postsLiveData.value = response.body()!!.data.posts
                }
                else{
                    return
                }
            }
            override fun onFailure(call: Call<Posts>, t: Throwable) {
                Log.d("TAG",t.message.toString())
            }
        })
    }
    fun observePostsLiveData() : LiveData<List<Post>> {
        return postsLiveData
    }
}